package org.example.carauction.CarModel;

import lombok.RequiredArgsConstructor;
import org.example.carauction.CarModel.Model.CarModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarModelService {

    CarModelRepository carModelRepository;

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
    }
}
