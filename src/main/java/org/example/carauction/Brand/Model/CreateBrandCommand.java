package org.example.carauction.Brand.Model;

public record CreateBrandCommand(
        String name,
        String countryOfOrigin
) {
}
