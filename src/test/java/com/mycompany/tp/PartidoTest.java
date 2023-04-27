/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp;
  import static org.junit.Assert.assertEquals;
  import org.junit.Before;
  import org.junit.Test;
/**
 *
 * @author Julio
 */

public class PartidoTest {
    private Equipo equipo1;
    private Equipo equipo2;
    private Partido partido ;
    
    @Before
      public void inicializo() {
        equipo1 = new Equipo("Argentina");
        equipo2 = new Equipo("Francia");
        partido = new Partido("1",equipo1,equipo2,"1");
        
        
       }
    
    @Test
    public void testPartidoEmpatado() {
        partido.setGolesEquipo1(3);
        partido.setGolesEquipo2(3);
        ResultadoEnum resultadoObtenido = partido.resultado(equipo1);
        assertEquals (ResultadoEnum.Empate, resultadoObtenido);
        
        
    }
    @Test
    public void testPartidoGanado() {
        partido.setGolesEquipo1(3);
        partido.setGolesEquipo2(2);
        ResultadoEnum resultadoObtenido = partido.resultado(equipo1);
        assertEquals (ResultadoEnum.Ganador, resultadoObtenido);
    }
    
    @Test
    public void testPartidoPerdido() {
        partido.setGolesEquipo1(1);
        partido.setGolesEquipo2(3);
        ResultadoEnum resultadoObtenido = partido.resultado(equipo1);
        assertEquals (ResultadoEnum.Perdedor, resultadoObtenido);
    }
    
    
}
