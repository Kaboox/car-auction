package org.example.carauction.Brand.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.carauction.CarModel.Model.CarModel;

import java.util.Set;

@Entity
@Table(name = "brand")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Brand {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @OneToMany(mappedBy = "brand")
    private Set<CarModel> carModel;
}
