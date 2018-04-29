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
public class Msg {
    private int id;
    private int emetteur_id;
    private int recepteur_id;
    private String subject;
    private String body;
    private String piece;
    private int lu;
    private String DateEnvoi;

    public Msg(int id, int emetteur_id, int recepteur_id, String subject, String body, String piece, int lu, String DateEnvoi) {
        this.id = id;
        this.emetteur_id = emetteur_id;
        this.recepteur_id = recepteur_id;
        this.subject = subject;
        this.body = body;
        this.piece = piece;
        this.lu = lu;
        this.DateEnvoi = DateEnvoi;
    }

    public Msg() {
    }

    

    @Override
    public String toString() {
        return "Msg{" + "id=" + id + ", emetteur_id=" + emetteur_id + ", recepteur_id=" + recepteur_id + ", subject=" + subject + ", body=" + body + ", piece=" + piece + ", lu=" + lu + ", DateEnvoi=" + DateEnvoi + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmetteur_id() {
        return emetteur_id;
    }

    public void setEmetteur_id(int emetteur_id) {
        this.emetteur_id = emetteur_id;
    }

    public int getRecepteur_id() {
        return recepteur_id;
    }

    public void setRecepteur_id(int recepteur_id) {
        this.recepteur_id = recepteur_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public int getLu() {
        return lu;
    }

    public void setLu(int lu) {
        this.lu = lu;
    }

    public String getDateEnvoi() {
        return DateEnvoi;
    }

    public void setDateEnvoi(String DateEnvoi) {
        this.DateEnvoi = DateEnvoi;
    }
    
}
