package org.example.carauction.CarModel.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.carauction.Brand.Model.Brand;
import org.example.carauction.common.Segment;

@Entity
@Table(name = "car_model")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarModel {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "segment")
    private Segment segment;

    @ManyToOne
    private Brand brand;

}
