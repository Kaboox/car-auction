package org.example.carauction.CarModel;

import lombok.RequiredArgsConstructor;
import org.example.carauction.Brand.BrandRepository;
import org.example.carauction.Brand.Model.Brand;
import org.example.carauction.CarModel.Model.CarModel;
import org.example.carauction.common.exception.ResourceAlreadyExistsException;
import org.example.carauction.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarModelService {

    private final CarModelRepository carModelRepository;
    private final BrandRepository brandRepository;

    public List<CarModel> getCarModels() {
        return carModelRepository.findAll();
    }

    public CarModel create(CarModel carModel) {
        if (carModel == null) {
            throw new IllegalArgumentException("Car model cannot be null");
        }

        if (carModel.getName() == null || carModel.getName().isBlank()) {
            throw new IllegalArgumentException("Car model name cannot be null or empty");
        }

        if (carModel.getSegment() == null) {
            throw new IllegalArgumentException("Car model segment cannot be null");
        }

//        Brand brand = brandRepository.findById(carModel.get)
//                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        String trimmedName = carModel.getName().trim();

        if (carModelRepository.existsByNameIgnoreCase(trimmedName)) {
            throw new ResourceAlreadyExistsException("Car model: " + trimmedName + " already exists in db");
        }

        carModel.setName(trimmedName);
//        carModel.setBrand(brand);

        return carModelRepository.save(carModel);
    }
}
