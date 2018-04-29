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
public class Rating{
    private int id;
    private int enseigne_id;
    private int user_id;
    private float note;

    public int getNbrVote() {
        return nbrVote;
    }

    public void setNbrVote(int nbrVote) {
        this.nbrVote = nbrVote;
    }

    public int getNbrEtoile() {
        return nbrEtoile;
    }

    public void setNbrEtoile(int nbrEtoile) {
        this.nbrEtoile = nbrEtoile;
    }
     private int nbrVote;
    private int nbrEtoile;
    public Rating(int id, int enseigne_id, int user_id, float note) {
        this.id = id;
        this.enseigne_id = enseigne_id;
        this.user_id = user_id;
        this.note = note;
    }

    public Rating() {
    }

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", enseigne_id=" + enseigne_id + ", user_id=" + user_id + ", note=" + note + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnseigne_id() {
        return enseigne_id;
    }

    public void setEnseigne_id(int enseigne_id) {
        this.enseigne_id = enseigne_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    
   
}


