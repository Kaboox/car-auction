package org.example.carauction.CarModel.Model;

import lombok.Builder;
import org.example.carauction.common.Segment;

@Builder
public record CreateCarModelCommand(
        String name,
        Segment segment,
        Long brandId
) {
    public CarModel toEntity() {
        return CarModel.builder()
                .name(name.trim())
                .segment(segment)
                .build();
    }
}
