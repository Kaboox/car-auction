package org.example.carauction.CarOffer;

import org.example.carauction.CarOffer.Model.CarOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarOfferRepository extends JpaRepository<CarOffer, Long> {
}
