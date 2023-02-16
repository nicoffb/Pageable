package com.salesianostriana.dam.restquery.services;

import com.salesianostriana.dam.restquery.Product;
import com.salesianostriana.dam.restquery.dto.page.PageDto;
import com.salesianostriana.dam.restquery.dto.product.ProductDto;
import com.salesianostriana.dam.restquery.repository.ProductRepository;
import com.salesianostriana.dam.restquery.search.specifications.PSBuilder;
import com.salesianostriana.dam.restquery.search.specifications.ProductSpecification;
import com.salesianostriana.dam.restquery.search.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public PageDto<ProductDto> search(List<SearchCriteria> params, Pageable pageable){
        PSBuilder psBuilder = new PSBuilder(params);

        Specification<Product> spec = psBuilder.build();
        Page<ProductDto> pageProductDto = productRepository.findAll(spec, pageable).map(ProductDto::of);

        return new PageDto<>(pageProductDto);
    }

}
