package org.example.carauction.CarOffer.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "car_offer")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CarOffer {

    @Id
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "vin")
    private String vin;

    @Column(name = "production_year")
    private Integer productionYear;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "price")
    private BigDecimal price;
}
