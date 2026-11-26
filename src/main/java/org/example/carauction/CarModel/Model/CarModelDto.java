package org.example.carauction.CarModel.Model;

import lombok.Builder;
import org.example.carauction.common.Segment;

@Builder
public record CarModelDto(
        Long id,
        String name,
        Segment segment,
        Long brandId
) {
    public static CarModelDto fromEntity(CarModel carModel) {
        return CarModelDto.builder()
                .id(carModel.getId())
                .name(carModel.getName())
                .segment(carModel.getSegment())
                .brandId(carModel.getBrand().getId())
                .build();
    }
}
