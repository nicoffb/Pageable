package com.salesianostriana.dam.restquery.dto.product;

import com.salesianostriana.dam.restquery.Product;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

    private Long id;
    private String name;
    private LocalDate releaseDate;
    private Double price;
    private boolean available;

    public static ProductDto of (Product p){
        return ProductDto.builder()
                .id(p.getId())
                .name(p.getProductName())
                .releaseDate(p.getReleaseDate())
                .price(p.getPrice())
                .available(p.isAvailable())
                .build();

    }

}
