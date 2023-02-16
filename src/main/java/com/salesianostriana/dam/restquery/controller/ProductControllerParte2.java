package com.salesianostriana.dam.restquery.controller;

//AQUI SE TRATAN DE HACER
//Trata de impedir que se hagan consultas usando como parámetro de búsqueda el id. Es decir: /product/?s=id:1 debería devolver todos los productos
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductControllerParte2 {

    private final ProductService productService;

    @GetMapping({"/",""})
    public ResponseEntity<PageDto<ProductDto>> findAll(
            @RequestParam(value = "s", defaultValue = "") String s,
            @RequestParam(value = "id", required = true) Long id, //SE AÑADE ESTA LINEA PARA QUE LA REQUIERA
            @PageableDefault(size = 25, page = 0)Pageable pageable){

        List<SearchCriteria> params = new ArrayList<>();
        if(s != null) {
            params = Extractor.extractSearchCriteriaList(s);
        }
        if(!params.contains(new SearchCriteria("id", ":", id))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        PageDto<ProductDto> res = productService.search(params, pageable);


        if(res.getContent().isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(res);
    }

   //  for (String s : search.split(",")) {
     //   String[] parts = s.split(":");
    //    if(parts[1].startsWith("*")) {
     //       params.add(new SearchCriteria(parts[0], "like", parts[1].substring(1) + "%"));
   //     } else {
   //         params.add(new SearchCriteria(parts[0], ":", parts[1]));
   //     }
   // }

}
