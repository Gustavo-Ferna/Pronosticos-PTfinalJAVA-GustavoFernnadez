/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp;

/**
 *
 * @author Julio
 */
public class Ronda {
    private String nro;
    private Partido partidos;

    public Ronda(String nro, Partido partidos) {
        super();
        this.nro = nro;
        this.partidos = partidos;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public Partido getPartidos() {
        return partidos;
    }

    public void setPartidos(Partido partidos) {
        this.partidos = partidos;
    }
    
    
    public int puntos(){
        return 0;
        
    }
    
    
}
