/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tp;

/**
 *
 * @author Julio
 * 
 */
    import java.io.IOException;
    import java.nio.file.*;
    import java.util.*;


public class Tp {

    public static void main(String[] args) {
        
        //Leer Resultados
             Collection<Partido> partidos = new ArrayList<Partido>();
             Collection<Ronda> rondas = new ArrayList<Ronda>();
             
            // Path pathResultados = Paths.get("src/test/java/resultados.csv");
             Path pathResultados = Paths.get(args[0]);
            
             List<String> lineasResultados = null;
    
             try {
               lineasResultados = Files.readAllLines(pathResultados);
                  
             } catch (IOException e){
               System.out.println("No se Pudo Leer el Archivo Resultados");
               System.out.println(e.getMessage());
               System.exit(1);
                     
                 }
               boolean primera = true;
               for (String lineaResultado:lineasResultados ){
                  
                   if (primera) {
                       primera = false;
                   } else{
                       
                       String[] campos = lineaResultado.split(",");
                       Equipo equipo1 = new Equipo(campos[1]);
                       Equipo equipo2 = new Equipo(campos[4]);
                       Partido partido = new Partido(equipo1,equipo2);
                       Ronda ronda = new Ronda(campos[0],partido);
                       rondas.add(ronda);
                       partido.setGolesEquipo1(Integer.parseInt(campos[2]));
                       partido.setGolesEquipo2(Integer.parseInt(campos[3]));
                       partidos.add(partido); //Agrego el Partido a la colección
                       
                        /*
                        for (Ronda rondaColeccion:rondas){
                               System.out.println(rondaColeccion.getNro());
                               System.out.println(rondaColeccion.getPartidos().getEquipo1().getNombre());
                               System.out.println(rondaColeccion.getPartidos().getEquipo2().getNombre());
                             
                        }
                        */
                       }
                
                  }
               
        
        //Leer pronostico
             int puntos = 0; 
             // Path pathPronosticos = Paths.get("src/test/java/pronostico.csv");
             Path pathPronosticos = Paths.get(args[1]);
             List<String> lineasPronosticos = null;

             try {
               lineasPronosticos = Files.readAllLines(pathPronosticos);
                  
             } catch (IOException e){
               System.out.println("No se Pudo Leer el Archivo de Pronósticos");
               System.out.println(e.getMessage());
               System.exit(1);
                     
                 }
               primera = true;
               String persona = "";
               for (String lineaPronostico:lineasPronosticos ){
                  
                   if (primera) {
                       primera = false;
                   } else{
                          String[] campos = lineaPronostico.split(",");
                          if (persona.equals(campos[0]) || persona.isEmpty()) {
                             persona = (campos[0]);
                          } else
                          {      System.out.println(persona + ":" + puntos);
                                 persona = (campos[0]);
                                 puntos = 0;
                          }            
                             
                          Equipo equipo1 = new Equipo(campos[1]);
                          Equipo equipo2 = new Equipo(campos[5]);
                          Partido partido = null;
                        
                          for (Partido partidoColeccion:partidos){
                             if (partidoColeccion.getEquipo1().getNombre().equals(equipo1.getNombre()) &&
                                  partidoColeccion.getEquipo2().getNombre().equals(equipo2.getNombre())) {
                                      {           
                                            //Busqueda en la Coleccion que coinicidan los 2 equipos
                                            partido = partidoColeccion;
                                            break;
                                 }
                             }      
                          } 
                          Equipo equipo = null;
                          ResultadoEnum resultado = null ;
                          if ("X".equals(campos[2])) {
                              equipo = equipo1;
                              resultado = ResultadoEnum.Ganador ;
                          }
                                 
                          if ("X".equals(campos[3])) {
                              equipo = equipo1;
                              resultado = ResultadoEnum.Empate ;
                          }
                          
                          if ("X".equals(campos[4])) {
                              equipo = equipo1;
                              resultado = ResultadoEnum.Perdedor ;
                          }
                                                    
                          Pronostico pronostico = new Pronostico(partido,equipo,resultado);
                          puntos += pronostico.puntos();
                          
                            //System.out.println(lineaPronostico);
             }
           }
               
               //Imprime el último participante
               System.out.println(persona + ":" + puntos);
               /*
               System.out.println("Los Puntos del Usuario Fueron:");
               System.out.println(puntos);
               */
        
        
    }
}
