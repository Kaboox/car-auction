package org.example.carauction.CarModel;

import org.example.carauction.CarModel.Model.CarModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarModelController {

    CarModelService carModelService;

    @GetMapping("/car-model")
    public List<CarModel> getCarModels() {
        return carModelService.getCarModels();
    }

    @PostMapping("/car-model")
    public CarModel create(@RequestBody CarModel carModel) {
        return carModelService.create(carModel);
    }
}
