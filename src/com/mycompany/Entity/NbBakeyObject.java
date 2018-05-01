/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entity;

/**
 *
 * @author Emir
 */
public class NbBakeyObject {
    public String brandname;
    public int nbbakery;

    public NbBakeyObject() {
    }

    public NbBakeyObject(String brandname, int nbbakery) {
        this.brandname = brandname;
        this.nbbakery = nbbakery;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public int getNbbakery() {
        return nbbakery;
    }

    public void setNbbakery(int nbbakery) {
        this.nbbakery = nbbakery;
    }
    
}
