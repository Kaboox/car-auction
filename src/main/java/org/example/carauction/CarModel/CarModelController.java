package org.example.carauction.CarModel;

import lombok.RequiredArgsConstructor;
import org.example.carauction.CarModel.Model.CarModel;
import org.example.carauction.CarModel.Model.CarModelDto;
import org.example.carauction.CarModel.Model.CreateCarModelCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/car-models")
public class CarModelController {

    private final CarModelService carModelService;

    @GetMapping
    public List<CarModelDto> getCarModels() {
        return carModelService.getCarModels();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarModelDto create(@RequestBody CreateCarModelCommand command) {
        return carModelService.create(command);
    }
}
