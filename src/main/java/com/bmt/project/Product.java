package com.bmt.project;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.bmt.project.model.ProductEntity;
import java.util.Map;

/**
 *
 * @author bruno
 */
public class Product {
    
    private Map<String, ProductEntity> mapProd;

    public Product(Map<String, ProductEntity> db) {
        mapProd = db;
    }
    
    public String getRef(final String name) {
        if (mapProd.containsKey(name)) {
            return String.valueOf(mapProd.get(name).getReference());
            //
        } else {
            throw new IllegalArgumentException("Given name doens't match any saved product.");
        }
    }
    
}
