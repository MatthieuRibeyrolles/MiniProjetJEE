/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmt.project.model;

/**
 *
 * @author Thibault
 */
public class DAO {
    
    protected final DataSourceFactory instance;
    
    public DAO (DataSourceFactory inst) {
        this.instance = inst;
    }
    
}
