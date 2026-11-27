package org.example.carauction.CarOffer.Model;

import org.example.carauction.Engine.Model.Engine;
import java.math.BigDecimal;

public record CreateCarOfferCommand(
        String title,
        String vin,
        Integer productionYear,
        Integer mileage,
        BigDecimal price, // czy to przekazuje w jsonie czy mapuje z stringa/inta w serwisie
        Engine engine,
        Boolean accidentFree,
        Long carModelId

) {
}
