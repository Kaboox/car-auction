package org.example.carauction.Engine.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.carauction.common.FuelType;
import org.example.carauction.common.Transmission;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Engine {

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission")
    private Transmission transmission;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "horse_power")
    private Integer horsePower;

}
