package org.example.carauction.Brand.Model;

import lombok.Builder;
import org.example.carauction.CarModel.Model.CarModel;

import java.util.Set;

@Builder
public record BrandDto(
        Long id,
        String name,
        String countryOfOrigin,
        Set<CarModel> carModels
) {
    public static BrandDto fromEntity(Brand brand) {
        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .countryOfOrigin(brand.getCountryOfOrigin())
                .carModels(brand.getCarModels())
                .build();
    }
}
