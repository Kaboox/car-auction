package org.example.carauction.CarOffer.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.carauction.CarModel.Model.CarModel;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Embedded
    private Engine engine;

    @Column(name = "accident_free")
    private Boolean accidentFree;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    private CarModel carModel;
}
