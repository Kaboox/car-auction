package org.example.carauction.Brand;

import lombok.RequiredArgsConstructor;
import org.example.carauction.Brand.Model.Brand;
import org.example.carauction.Brand.Model.CreateBrandCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/brands")
    public List<Brand> getBrands() {
        return brandService.getAll();
    }

    @PostMapping("/brands")
    @ResponseStatus(HttpStatus.CREATED)
    public Brand create(@RequestBody CreateBrandCommand createBrandCommand) {
       return brandService.create(createBrandCommand);

    }
}
