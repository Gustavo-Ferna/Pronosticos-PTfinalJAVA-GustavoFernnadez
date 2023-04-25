/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp;

/**
 *
 * @author Julio
 */
public class Pronostico {
    
    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultado;
    private int PuntosGanador;
    
    public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultado,int PuntosGanador) {
       super();
       this.partido = partido;
       this.equipo = equipo;
       this.resultado = resultado;
       this.PuntosGanador = PuntosGanador;
       
       
    }

    public Partido getPartido() {
        return partido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public ResultadoEnum getResultado() {
        return resultado;
    }

    
    
     public int puntos(){
           ResultadoEnum resultReal = partido.resultado(equipo);
           //Completar
            if (resultReal.equals(resultado)) {
                return PuntosGanador;
           } else {
                return 0;
               }
            }
    
        public int aciertos(){
           ResultadoEnum resultReal = partido.resultado(equipo);
           //Completar
            if (resultReal.equals(resultado)) {
                return 1;
           } else {
                return 0;
               }
            }
    

}
 

