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
public class Enseigne {
     private int id;
    private String name;
    private String address;
    private String PhoneNumber;
    private String Fax;
    private String email;
    private String WebSite;
    private String logo;
    private String CodeRc;
    private String description;
    private int id_user;
    private int NbReclamation;

    public Enseigne(int id, String name, String address, String PhoneNumber, String Fax, String email, String WebSite, String logo, String CodeRc, String description, int id_user, int NbReclamation) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.PhoneNumber = PhoneNumber;
        this.Fax = Fax;
        this.email = email;
        this.WebSite = WebSite;
        this.logo = logo;
        this.CodeRc = CodeRc;
        this.description = description;
        this.id_user = id_user;
        this.NbReclamation = NbReclamation;
    }
    public Enseigne(int id, int user_id, String Name, String Address, String PhoneNumber, String Fax, String Email, String Website, String Logo, String CodeRC, String Description, int nbClaims) {
        this.id = id;
        this.id_user = user_id;
        this.name = Name;
        this.address = Address;
        this.PhoneNumber = PhoneNumber;
        this.Fax = Fax;
        this.email = Email;
        this.WebSite = Website;
        this.logo = Logo;
        this.CodeRc = CodeRC;
        this.description = Description;
        this.NbReclamation = nbClaims;
    }

    public Enseigne(int user_id, String Name, String Address, String PhoneNumber, String Fax, String Email, String Website, String Logo, String CodeRC, String Description, int nbClaims) {
        this.id_user = user_id;
        this.name = Name;
        this.address = Address;
        this.PhoneNumber = PhoneNumber;
        this.Fax = Fax;
        this.email = Email;
        this.WebSite = Website;
        this.logo = Logo;
        this.CodeRc = CodeRC;
        this.description = Description;
        this.NbReclamation = nbClaims;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebSite() {
        return WebSite;
    }

    public void setWebSite(String WebSite) {
        this.WebSite = WebSite;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCodeRc() {
        return CodeRc;
    }

    public void setCodeRc(String CodeRc) {
        this.CodeRc = CodeRc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getNbReclamation() {
        return NbReclamation;
    }

    public void setNbReclamation(int NbReclamation) {
        this.NbReclamation = NbReclamation;
    }
   

    public Enseigne() {
    }

    @Override
    public String toString() {
        return "Enseigne{" + "id=" + id + ", name=" + name + ", address=" + address + ", PhoneNumber=" + PhoneNumber + ", Fax=" + Fax + ", email=" + email + ", WebSite=" + WebSite + ", logo=" + logo + ", CodeRc=" + CodeRc + ", description=" + description + ", id_user=" + id_user + ", NbReclamation=" + NbReclamation + '}';
    }

    
}
