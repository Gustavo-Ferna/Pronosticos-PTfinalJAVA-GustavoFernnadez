/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp;

/**
 *
 * @author Julio
 */
public class Participante {
    private String nombre;
    private int aciertos;
    private int puntosxronda;
    private int puntosxfase;

    public Participante(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getPuntosxronda() {
        return puntosxronda;
    }

    public void setPuntosxronda(int puntosxronda) {
        this.puntosxronda = puntosxronda;
    }

    public int getPuntosxfase() {
        return puntosxfase;
    }

    public void setPuntosxfase(int puntosxfase) {
        this.puntosxfase = puntosxfase;
    }
    
    
    
    
}
