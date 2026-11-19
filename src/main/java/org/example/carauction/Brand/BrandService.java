package org.example.carauction.Brand;

import lombok.RequiredArgsConstructor;
import org.example.carauction.Brand.Model.Brand;
import org.example.carauction.Brand.Model.CreateBrandCommand;
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

    public Brand create(CreateBrandCommand createBrandCommand) {
        if (createBrandCommand == null) {
            throw new IllegalArgumentException("Cannot pass null brand");
        }

        if (createBrandCommand.name() == null || createBrandCommand.name().isBlank()) {
            throw new IllegalArgumentException("Brand name cannot be null or empty");
        }

        if (createBrandCommand.countryOfOrigin()== null || createBrandCommand.countryOfOrigin().isBlank()) {
            throw new IllegalArgumentException("Brand country of origin cannot be null or empty");
        }

        if (brandRepository.existsByNameIgnoreCase(createBrandCommand.name().trim())) {
            throw new ResourceAlreadyExistsException("Brand: " + createBrandCommand.name() + " already exists in db");
        }

        Brand brandToSave= new Brand();

        brandToSave.setName(createBrandCommand.name().trim());
        brandToSave.setCountryOfOrigin(createBrandCommand.countryOfOrigin().trim());

        return brandRepository.save(brandToSave);
    }
}
