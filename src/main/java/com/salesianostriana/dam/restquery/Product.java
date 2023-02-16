package com.salesianostriana.dam.restquery;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private double price;

    private boolean available;

}
