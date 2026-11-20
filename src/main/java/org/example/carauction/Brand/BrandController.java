package org.example.carauction.Brand;

import lombok.RequiredArgsConstructor;
import org.example.carauction.Brand.Model.BrandDto;
import org.example.carauction.Brand.Model.CreateBrandCommand;
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
@RequestMapping("/api/v1/brands")
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public List<BrandDto> getBrands() {
        return brandService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrandDto create(@RequestBody CreateBrandCommand createBrandCommand) {
       return brandService.create(createBrandCommand);

    }
}
