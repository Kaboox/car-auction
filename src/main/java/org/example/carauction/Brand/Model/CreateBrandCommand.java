package org.example.carauction.Brand.Model;

import java.util.Set;

public record CreateBrandCommand(
        String name,
        String countryOfOrigin
) {
    public Brand toEntity() {
        return Brand.builder()
                .name(name.trim())
                .countryOfOrigin(countryOfOrigin.trim())
                .carModels(Set.of())
                .build();
    }
}
