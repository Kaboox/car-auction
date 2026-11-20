package org.example.carauction.Brand.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true) // unique zeby nie bylo race condition gdy np 2 userow doda na raz
    private String name;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @OneToMany(mappedBy = "brand")
    private Set<CarModel> carModels;
}
