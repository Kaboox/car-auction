package org.example.carauction.Brand.Model;

import lombok.Builder;
import org.example.carauction.CarModel.Model.CarModel;
import org.example.carauction.CarModel.Model.CarModelDto;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record BrandDto(
        Long id,
        String name,
        String countryOfOrigin,
        Set<CarModelDto> carModels
) {
    public static BrandDto fromEntity(Brand brand) {
        Set<CarModelDto> dtos = brand.getCarModels() == null ? Set.of() :
                brand.getCarModels()
                        .stream()
                        .map(CarModelDto::fromEntity)
                        .collect(Collectors.toSet());

        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .countryOfOrigin(brand.getCountryOfOrigin())
                .carModels(dtos)
                .build();
    }
}
