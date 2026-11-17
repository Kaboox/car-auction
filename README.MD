# 🚗 Car Marketplace API (Mini-Otomoto)

Projekt REST API do zarządzania ogłoszeniami motoryzacyjnymi, stworzony w celu przećwiczenia zaawansowanych mechanizmów **Spring Boot** oraz **Spring Data JPA** (relacje encji, kompozycja `@Embeddable`, `JpaSpecificationExecutor`, paginacja, sortowanie, walidacja i globalna obsługa wyjątków).

---

## 🛠️ Stack Technologiczny & Konfiguracja

* **Java 21+** & **Spring Boot 3.x**
* **Spring Web** (REST API)
* **Spring Data JPA** (Hibernate, `JpaSpecificationExecutor`, `Pageable`)
* **Bean Validation** (`jakarta.validation`)
* **PostgreSQL** (baza danych)
* **Lombok**
* **Springdoc OpenAPI / Swagger UI** (opcjonalnie do testów)

### Uruchomienie bazy danych (Docker - Windows 1-liner)
```bash
docker run -d --name car-marketplace-db -e POSTGRES_DB=car_marketplace -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -p 5432:5432 postgres:17-alpine
```

### Konfiguracja `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/car_marketplace
spring.datasource.username=admin
spring.datasource.password=admin

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## 🗄️ 1. Model Danych (Encje, Relacje, Kompozycja)

### `Brand` (Marka) & `CarModel` (Model)
* **Relacja:** `Brand` `1:N` `CarModel`
* **Pola `Brand`:**
  * `id` (`Long`)
  * `name` (`String` – unikalne, np. BMW, Audi, Toyota)
  * `countryOfOrigin` (`String`)
* **Pola `CarModel`:**
  * `id` (`Long`)
  * `name` (`String` – np. Seria 3, A4, Corolla)
  * `segment` (`Enum`: `SEDAN`, `SUV`, `COMBI`, `HATCHBACK`, `COUPE`)

### `Engine` (Value Object – `@Embeddable`)
Kompozycja osadzona w encji `CarOffer` (`@Embedded`), grupująca parametry układu napędowego w jednej tabeli bazodanowej:
* `fuelType` (`Enum`: `PETROL`, `DIESEL`, `HYBRID`, `ELECTRIC`, `LPG`)
* `transmission` (`Enum`: `MANUAL`, `AUTOMATIC`)
* `capacity` (`Integer` – pojemność w $cm^3$, np. `1995`; `null` lub `0` dla `ELECTRIC`)
* `horsePower` (`Integer` – moc w KM, $> 0$)

### `CarOffer` (Ogłoszenie – główna encja)
* **Relacja:** `CarOffer` `N:1` `CarModel`
* **Pola wymagane:**
  * `id` (`Long` / `UUID`)
  * `title` (`String` – np. *"BMW 320d M-Pakiet Bezwypadkowy"*)
  * `vin` (`String` – dokładnie 17 znaków, unikalny w obrębie aktywnych/zarezerwowanych ogłoszeń)
  * `productionYear` (`Integer` – np. `1990`–`2026`)
  * `mileage` (`Integer` – przebieg w km, $\ge 0$)
  * `price` (`BigDecimal` – cena w PLN, $> 0$)
  * `engine` (`Engine` – `@Embedded`)
  * `accidentFree` (`Boolean`)
  * `status` (`Enum`: `ACTIVE`, `RESERVED`, `SOLD`, `ARCHIVED`)
  * `createdAt` (`LocalDateTime` – ustawiane automatycznie przy zapisie)

---

## 🌐 2. Kontrakt API (Endpointy REST)

> **Ważne:** Nie zwracaj bezpośrednio encji z kontrolera – używaj obiektów DTO i mappera. Odpowiedzi listujące powinny zwracać `Page<CarOfferResponseDto>` (lub własny wrapper paginacji).

| Metoda | Endpoint | Opis | Kody HTTP |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/brands` | Dodanie nowej marki | `201`, `400`, `409` |
| `POST` | `/api/v1/brands/{brandId}/models` | Dodanie modelu do marki | `201`, `404`, `409` |
| `GET` | `/api/v1/brands` | Pobranie listy marek wraz z ich modelami | `200` |
| `POST` | `/api/v1/offers` | Wystawienie nowego ogłoszenia | `201`, `400`, `404`, `409` |
| `GET` | `/api/v1/offers/{id}` | Szczegóły pojedynczego ogłoszenia | `200`, `404` |
| `GET` | `/api/v1/offers` | **Główna wyszukiwarka** (Specification + Pageable) | `200`, `400` |
| `PUT` | `/api/v1/offers/{id}` | Pełna aktualizacja ogłoszenia | `200`, `400`, `404` |
| `PATCH` | `/api/v1/offers/{id}/status` | Zmiana statusu ogłoszenia (np. na `SOLD`) | `200`, `400`, `404` |
| `DELETE` | `/api/v1/offers/{id}` | Usunięcie lub soft-delete (`ARCHIVED`) | `204`, `404` |

---

## 🔍 3. Wyszukiwarka (`JpaSpecificationExecutor`)

Endpoint `GET /api/v1/offers` przyjmuje opcjonalne kryteria wyszukiwania (`@RequestParam` lub obiekt `CarOfferSearchCriteria`). Brak parametru oznacza brak filtrowania po tym polu.

* **Filtrowanie po relacjach (`Join` w Specification):**
  * `brandId` (`Long`) lub `brandName` (`String`, case-insensitive) – przejście po relacji `CarOffer -> CarModel -> Brand`.
  * `modelId` (`Long`)
  * `segment` (`Enum` – z encji `CarModel`)
* **Filtrowanie po polach kompozycji (`root.get("engine").get(...)`):**
  * `fuelTypes` (`List<FuelType>`) – wyszukiwanie operatorem `IN`, np. `?fuelTypes=PETROL,HYBRID`.
  * `transmission` (`Transmission`)
  * `horsePowerFrom` (`Integer`)
* **Zakresy liczbowe (od – do):**
  * `priceFrom`, `priceTo` (`BigDecimal`)
  * `yearFrom`, `yearTo` (`Integer`)
  * `mileageFrom`, `mileageTo` (`Integer`)
* **Pozostałe filtry i wyszukiwanie tekstowe:**
  * `accidentFree` (`Boolean`)
  * `status` (`OfferStatus`) – jeśli nie podano, domyślnie zwracaj wyłącznie `ACTIVE`.
  * `keyword` (`String`) – wyszukiwanie frazy (`LIKE %keyword%`, case-insensitive) w polu `title` **LUB** w nazwie modelu (`carModel.name`).

---

## 📄 4. Paginacja i Sortowanie (`Pageable`)

* **Domyślne ustawienia (`@PageableDefault`):** strona `0`, rozmiar `10`, sortowanie po `createdAt` malejąco (`DESC`).
* **Dozwolone pola sortowania:** `price`, `productionYear`, `mileage`, `createdAt`, `engine.horsePower` (możesz też zmapować przyjazny alias `horsePower` na `engine.horsePower`).
* **Zabezpieczenia:**
  * Zablokuj sortowanie po polach wrażliwych (np. `vin`) lub nieistniejących – rzuć własny wyjątek `InvalidSortFieldException` -> `400 Bad Request`.
  * Ogranicz maksymalny rozmiar strony (np. `max size = 50`).

---

## 🛡️ 5. Walidacja i Obsługa Wyjątków (`@RestControllerAdvice`)

Zaimplementuj globalny handler wyjątków zwracający spójny obiekt błędu (`timestamp`, `status`, `error`, `message`, `fieldErrors`).

### Reguły biznesowe i walidacyjne:
1. **Numer VIN:** Musi składać się z dokładnie 17 znaków alfanumerycznych z wyłączeniem liter `I`, `O`, `Q` (standard ISO). Jeśli istnieje już ogłoszenie ze statusem `ACTIVE` lub `RESERVED` o tym samym numerze VIN, odrzuć żądanie kodem `409 Conflict`.
2. **Walidacja silnika (`Engine`):** Jeśli `fuelType == ELECTRIC`, pole `capacity` musi być `null` lub równe `0`. Dla każdego innego paliwa `capacity` jest wymagane i musi być $> 0$.
3. **Walidacja zakresów wyszukiwarki:** Jeśli `priceFrom > priceTo` lub `yearFrom > yearTo` (lub `mileageFrom > mileageTo`), zwróć `400 Bad Request`.
4. **Maszyna stanów ogłoszenia:** Ogłoszenia ze statusem `SOLD` nie można z powrotem zmienić na `ACTIVE` (wyjątek biznesowy -> `400 Bad Request`).

---

## ✅ 6. Checklista Dodatkowa (Bonusy)

- [ ] **Data Seeder (`CommandLineRunner`):** Automatyczne zasilenie bazy przy starcie aplikacji (na profilu `dev`) danymi startowymi: 4–5 marek, po 2–3 modele na markę oraz ok. 20–25 zróżnicowanych ogłoszeń.
- [ ] **Analiza problemu N+1:** Sprawdzenie logów SQL podczas wywołania `GET /api/v1/offers` i optymalizacja pobierania relacji (`@EntityGraph` lub `JOIN FETCH` w `Specification` z zachowaniem poprawnej paginacji po stronie bazy danych).