package org.example.carauction.CarOffer.Model;

import org.example.carauction.Engine.Model.Engine;
import org.example.carauction.common.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CarOfferDto(
        Long id,
        String title,
        String vin,
        Integer productionYear,
        Integer mileage,
        BigDecimal price,
        Engine engine,
        Boolean accidentFree,
        Status status,
        LocalDateTime createdAt,
        Long carModelId
) {
}
