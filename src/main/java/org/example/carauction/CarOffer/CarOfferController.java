package org.example.carauction.CarOffer;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.carauction.CarOffer.Model.CarOffer;
import org.example.carauction.CarOffer.Model.CreateCarOfferCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offers")
public class CarOfferController {

    CarOfferRepository carOfferRepository;

    @GetMapping
    public List<CarOffer> getAll() {
        return carOfferRepository.findAll();
    }

    @PostMapping
    public CarOffer create(@RequestBody CreateCarOfferCommand) {

    }

}
