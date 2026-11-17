package org.example.carauction.CarOffer.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.carauction.Engine.Model.Engine;
import org.example.carauction.common.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "car_offer")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CarOffer {

    @Id
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "vin")
    private String vin;

    @Column(name = "production_year")
    private Integer productionYear;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "engine")
    @Embedded
    private Engine engine;

    @Column(name = "accident_free")
    private Boolean accidentFree;

    @Column(name = "status")
    private Status status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
