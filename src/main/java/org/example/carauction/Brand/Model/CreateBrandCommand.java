package org.example.carauction.Brand.Model;

import java.util.Set;

public record CreateBrandCommand(
        String name,
        String countryOfOrigin
) {
    public Brand toEntity() {
        return Brand.builder()
                .name(name)
                .countryOfOrigin(countryOfOrigin)
                .carModels(Set.of())
                .build();
    }
}
