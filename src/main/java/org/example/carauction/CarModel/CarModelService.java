package org.example.carauction.CarModel;

import lombok.RequiredArgsConstructor;
import org.example.carauction.Brand.BrandRepository;
import org.example.carauction.Brand.Model.Brand;
import org.example.carauction.CarModel.Model.CarModel;
import org.example.carauction.CarModel.Model.CarModelDto;
import org.example.carauction.CarModel.Model.CreateCarModelCommand;
import org.example.carauction.common.exception.ResourceAlreadyExistsException;
import org.example.carauction.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarModelService {

    private final CarModelRepository carModelRepository;
    private final BrandRepository brandRepository;

    public List<CarModelDto> getCarModels() {
        return carModelRepository.findAll()
                .stream().map(CarModelDto::fromEntity)
                .toList();
    }

    public CarModelDto create(CreateCarModelCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("Car model cannot be null");
        }

        if (command.name() == null || command.name().isBlank()) {
            throw new IllegalArgumentException("Car model name cannot be null or empty");
        }

        if (command.segment() == null) {
            throw new IllegalArgumentException("Car model segment cannot be null");
        }

        Brand brand = brandRepository.findById(command.brandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));


        CarModel carModel = command.toEntity();
        carModel.setBrand(brand);

        return CarModelDto.fromEntity(carModelRepository.save(carModel));
    }
}
