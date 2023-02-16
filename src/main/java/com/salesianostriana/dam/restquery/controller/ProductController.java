package com.salesianostriana.dam.restquery.controller;

import com.salesianostriana.dam.restquery.Product;
import com.salesianostriana.dam.restquery.dto.page.PageDto;
import com.salesianostriana.dam.restquery.dto.product.ProductDto;
import com.salesianostriana.dam.restquery.search.util.Extractor;
import com.salesianostriana.dam.restquery.search.util.SearchCriteria;
import com.salesianostriana.dam.restquery.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<PageDto<ProductDto>> findAll(
            @RequestParam(value = "s", defaultValue = "") String s,
            @PageableDefault(size = 25, page = 0)Pageable pageable){

        List<SearchCriteria> params = Extractor.extractSearchCriteriaList(s);
        PageDto<ProductDto> res = productService.search(params, pageable);

        if(res.getContent().isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(res);
    }



}
