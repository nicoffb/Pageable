package com.salesianostriana.dam.restquery.search.specifications;

import com.salesianostriana.dam.restquery.Product;
import com.salesianostriana.dam.restquery.search.util.SearchCriteria;

import java.util.List;

public class PSBuilder extends GSBuilder<Product>{
    public PSBuilder(List<SearchCriteria> params) {
        super(params, Product.class);
    }
}
