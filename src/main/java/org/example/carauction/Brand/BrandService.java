package org.example.carauction.Brand;

import lombok.RequiredArgsConstructor;
import org.example.carauction.Brand.Model.Brand;
import org.example.carauction.common.exception.ResourceAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    public Brand create(Brand brand) {
        if (brand == null) {
            throw new IllegalArgumentException("Cannot pass null brand");
        }

        if (brand.getName() == null || brand.getName().isBlank()) {
            throw new IllegalArgumentException("Brand name cannot be null or empty");
        }

        if (brand.getCountryOfOrigin() == null || brand.getCountryOfOrigin().isBlank()) {
            throw new IllegalArgumentException("Brand country of origin cannot be null or empty");
        }

        if (brandRepository.existsByNameIgnoreCase(brand.getName().trim())) {
            throw new ResourceAlreadyExistsException("Brand: " + brand.getName() + " already exists in db");
        }

        brand.setName(brand.getName().trim());
        brand.setCountryOfOrigin(brand.getCountryOfOrigin().trim());

        return brandRepository.save(brand);
    }
}
