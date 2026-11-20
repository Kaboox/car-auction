package org.example.carauction.Brand;

import lombok.RequiredArgsConstructor;
import org.example.carauction.Brand.Model.Brand;
import org.example.carauction.Brand.Model.BrandDto;
import org.example.carauction.Brand.Model.CreateBrandCommand;
import org.example.carauction.common.exception.ResourceAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public List<BrandDto> getAll() {
        return brandRepository.findAll()
                .stream()
                .map(BrandDto::fromEntity)
                .toList();
    }

    public BrandDto create(CreateBrandCommand createBrandCommand) {
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

        Brand brandToSave = createBrandCommand.toEntity();

        return BrandDto.fromEntity(brandRepository.save(brandToSave));
    }
}
