package org.example.carauction.CarOffer.Model;

import org.example.carauction.Engine.Model.Engine;
import java.math.BigDecimal;

public record CreateCarOfferCommand(
        String title,
        String vin,
        Integer productionYear,
        Integer mileage,
        BigDecimal price,
        Engine engine,
        Boolean accidentFree,
        Long carModelId

) {
}
