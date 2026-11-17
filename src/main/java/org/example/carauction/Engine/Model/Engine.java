package org.example.carauction.Engine.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.carauction.common.FuelType;

@Entity
@Table(name = "engine")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Engine {

    @Id
    private Long id;

    @Column(name = "fuel_type")
    private FuelType fuelType;


}
