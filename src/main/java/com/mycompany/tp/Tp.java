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
    import java.sql.*;
    import java.util.*;


public class Tp {

    public static void main(String[] args) throws SQLException {
        
        //Leer Resultados
             Collection<Partido> partidos = new ArrayList<Partido>();
             Collection<Ronda> rondas = new ArrayList<Ronda>();
             Collection<Fase> fases = new ArrayList<Fase>();
             
             Path pathConfiguracion = Paths.get(args[0]);
             List<String> lineasConfiguracion = null;
    
             try {
               lineasConfiguracion = Files.readAllLines(pathConfiguracion);
                      
             } catch (IOException e){
               System.out.println("No se Pudo Leer el Archivo de Configuraciones");
               System.out.println(e.getMessage());
               System.exit(1);
                 }
     
               String bd="";
               String url="";
               String user="";
               String password="";
               String driver="";
               int PuntosGanador=0;
               int PuntosExtraRonda=0;
               int PuntosExtraFase=0;
               
               //Lee el Archivo Configuracion
               for (String lineaconfiguracion:lineasConfiguracion){
                   if (lineaconfiguracion.contains("bd=")){
                       bd = lineaconfiguracion.substring(3);
                   }
                   
                   if (lineaconfiguracion.contains("url=")){
                       url = lineaconfiguracion.substring(4);
                   }
                   
                   if (lineaconfiguracion.contains("user=")){
                       user= lineaconfiguracion.substring(5);
                   }
                       
                   if (lineaconfiguracion.contains("password=")){
                       password= lineaconfiguracion.substring(9);
                   }
                   
                    if (lineaconfiguracion.contains("driver=")){
                       driver= lineaconfiguracion.substring(7);
                   }
                   
                   if (lineaconfiguracion.contains("PuntosGanador=")){
                       PuntosGanador= Integer.parseInt(lineaconfiguracion.substring(14));
                   }
               
                    if (lineaconfiguracion.contains("PuntosExtraRonda=")){
                       PuntosExtraRonda= Integer.parseInt(lineaconfiguracion.substring(17));
                   }
               
                    if (lineaconfiguracion.contains("PuntosExtraFase=")){
                       PuntosExtraFase= Integer.parseInt(lineaconfiguracion.substring(16));
                   }
               }
             
             
             ConexionBD cn = new ConexionBD(bd,url,user,password,driver);
             cn.conexion();
              
            Statement st = cn.conn.createStatement();
            //Crea las tablas del Sistema
            //Tabla Equipo
            //st.execute("drop table if exists equipo;"); 
            //st.execute ("create table equipo (nombre varchar(255),descripcion varchar(255));");
            
            //Tabla pronosticos
           
            st.execute("drop table if exists pronosticos;"); 
            st.execute ("create table pronosticos (participante varchar(255)," +
                        "equipo1 varchar(255),gana1 char(1),empata char(1)," + 
                        "gana2 char(1),equipo2 varchar(255))");
             
            // Path pathResultados = Paths.get("src/test/java/resultados.csv");
             Path pathResultados = Paths.get(args[1]);
            
             List<String> lineasResultados = null;
    
             try {
               lineasResultados = Files.readAllLines(pathResultados);
                      
             } catch (IOException e){
               System.out.println("No se Pudo Leer el Archivo Resultados");
               System.out.println(e.getMessage());
               System.exit(1);
                     
                 }
               boolean primera = true;
               String nroronda="";
               String nrofase="";
               String ultronda="";
               String ultfase="";
               int cantpartidosrondas = 0;
               int cantpartidosfases = 0;
               
               for (String lineaResultado:lineasResultados ){
                  
                   if (primera) {
                       primera = false;
                   } else{
                       String[] campos = lineaResultado.split(",");
                       Equipo equipo1 = new Equipo(campos[1]);
                       Equipo equipo2 = new Equipo(campos[4]);
                       nroronda = campos[0];
                       nrofase = campos[5];
                       if (ultronda.isEmpty())  {
                          ultronda = nroronda; }
                       else {
                          if (!nroronda.equals(ultronda)) {
                               Ronda ronda = new Ronda(ultronda,cantpartidosrondas);
                               rondas.add(ronda);
                               cantpartidosrondas = 0;
                               ultronda = "";
                             }       
                         
                         }
                    
                       if (ultfase.isEmpty())  {
                          ultfase = nrofase; }
                       else {
                          if (!nrofase.equals(ultfase)) {
                               Fase fase = new Fase(ultfase,cantpartidosfases);
                               fases.add(fase);
                               cantpartidosfases = 0;
                               ultfase = "";
                              
                             }       
                         
                         }
                         
                            cantpartidosrondas += 1;
                            cantpartidosfases += 1;
                            Partido partido = new Partido(nroronda,equipo1,equipo2,nrofase);
                            partido.setGolesEquipo1(Integer.parseInt(campos[2]));
                            partido.setGolesEquipo2(Integer.parseInt(campos[3]));
                            partidos.add(partido); //Agrego el Partido a la colección
                          
                       }
                       
                     
                  }
                       Ronda ronda = new Ronda(ultronda,cantpartidosrondas);
                       rondas.add(ronda);
                       Fase fase = new Fase(ultfase,cantpartidosfases);
                       fases.add(fase);
                       
               
               
               
             //Inserta Archivo pronostico en tabla Pronosticos
               
             Path pathRegPronosticos = Paths.get(args[2]);
             List<String> regPronosticos = null;

             try {
               regPronosticos = Files.readAllLines(pathRegPronosticos);
                  
             } catch (IOException e){
               System.out.println("No se Pudo Leer el Archivo de Pronósticos");
               System.out.println(e.getMessage());
               System.exit(1);
                     
             }
               primera = true;
               for (String regPronostico:regPronosticos ){
                  
                   if (primera) {
                       primera = false;
                   } else{
                          String[] campos = regPronostico.split(",");
                          String sql="";
                          st.execute( "insert into pronosticos (participante,equipo1,gana1,empata,gana2,equipo2)" +
                                " values (" + "'" + campos[0] + "'" + "," + "'" + campos[1] + "'" + ","  +
                                "'" + campos[2] + "'" + "," + "'" + campos[3] + "'" + "," + 
                                "'" + campos[4] + "'" + "," + "'" + campos[5] + "'" + ")") ;
               
                   }
               }
               
               //Obtenermos los pronósticos
               try {
                 int puntos=0;
                 int puntosRonda=0;
                 int puntosFase=0;
                 int partidosacertadosxronda=0;
                 int partidosacertadosxfase=0;
                 int partidosxronda=0;
                 int partidosxfase=0;
                 String participante="";
                 ResultSet rs = st.executeQuery("select * from pronosticos order by participante");
                 //Imprime Encabezado Resultado
                 System.out.println("-----------------------------------------------------------------------------------------------------------");
                 System.out.println("|                              Resultado de Puntajes x Participante                                       |");
                 System.out.println("-----------------------------------------------------------------------------------------------------------");
                
                 System.out.println("|    Participante   |     Aciertos    |   Puntos X Ronda   |  Puntos x Fase  |      Total de Puntos       |");
                 System.out.println("-----------------------------------------------------------------------------------------------------------");
                                                   
                 while (rs.next()){
                      if (participante.equals(rs.getString(1)) || participante.isEmpty()) {
                          participante = rs.getString(1);
                      } else {
                                      ImprimeResultado (participante,puntos,puntosRonda,puntosFase);                                                                        
                                      participante = rs.getString(1);
                                      puntos = 0;
                                      puntosRonda = 0;
                                      puntosFase = 0;
                                      partidosacertadosxronda = 0;
                                      partidosacertadosxfase = 0;
                      
                      } 
                          Equipo equipo1 = new Equipo(rs.getString((2)));
                          Equipo equipo2 = new Equipo(rs.getString((6)));
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
                          if ("X".equals(rs.getString((3)))) {
                              equipo = equipo1;
                              resultado = ResultadoEnum.Ganador ;
                          }
                                 
                          if ("X".equals(rs.getString((4)))) {
                              equipo = equipo1;
                              resultado = ResultadoEnum.Empate ;
                          }
                          
                          if ("X".equals(rs.getString((5)))) 
                            {
                              equipo = equipo1;
                              resultado = ResultadoEnum.Perdedor ;
                          }
                                                    
                          Pronostico pronostico = new Pronostico(partido,equipo,resultado,PuntosGanador);
                          puntos += pronostico.puntos();
                          partidosacertadosxronda += pronostico.aciertos();
                          partidosacertadosxfase += pronostico.aciertos();
                          
                          
                          //Calcula los Puntos x Ronda
                          for (Ronda rondaColleccion:rondas) {
                             if (rondaColleccion.getNro().equals(partido.getRonda())) {
                                 partidosxronda = rondaColleccion.getPartidos();
                                 break;
                             }
                              
                          }
                          if (partidosacertadosxronda == partidosxronda) {
                              puntosRonda += PuntosExtraRonda;
                              partidosacertadosxronda=0;
                              partidosxronda = 0;
                          
                          } 
                          
                   
                            //Calcula los Puntos x Fase
                           for (Fase faseColleccion:fases) {
                             if (faseColleccion.getNro().equals(partido.getFase())) {
                                 partidosxfase = faseColleccion.getPartidos();
                                 break;
                             }
                              
                          }
                          if (partidosacertadosxfase == partidosxfase) {
                              puntosFase += PuntosExtraFase;
                              partidosacertadosxfase=0;
                              partidosxfase = 0;
                          
                          }
                     
                }
                 
                 //System.out.println(participante + ":" + " Aciertos:" + puntos + " | " + "Puntos x Ronda:" + puntosRonda + " | " + "Puntos x Fase:" + puntosFase + " | " + "Total:" + (puntos+puntosRonda+puntosFase));
                 //System.out.println("|      " + participante + "     " + "|" +  puntos + "    | " + puntosRonda + " | " + puntosFase + " | " + (puntos+puntosRonda+puntosFase) + "|");
                 ImprimeResultado (participante,puntos,puntosRonda,puntosFase);                                                                        
               
         
               
           } catch (SQLException e) {
                  
               System.out.println("No se Pudo Leer la Tabla Pronósticos");
               System.out.println(e.getMessage());
               System.exit(1);
               cn.conexion().close();
           }
               
        
        
    }

  public static void ImprimeResultado(String participante,int puntos, int puntosRonda,int  puntosFase) {
                 //System.out.println(participante + ":" + " Aciertos:" + puntos + " | " + "Puntos x Ronda:" + puntosRonda + " | " + "Puntos x Fase:" + puntosFase + " | " + "Total:" + (puntos+puntosRonda+puntosFase));
                 //System.out.println("|      " + participante + "     " + "|" +  puntos + "    | " + puntosRonda + " | " + puntosFase + " | " + (puntos+puntosRonda+puntosFase) + "|");
                 String salidaParticipante = String.format("%10s",participante);
                 String salidaPuntos = String.format("%20s",puntos);
                 String salidaRonda = String.format("%20s",puntosRonda);
                 String salidaFase = String.format("%20s",puntosFase );
                 String salidaTotal = String.format("%20s",puntos+puntosRonda+puntosFase );
                 String salidaCierre = String.format("%16s","|" );
                 System.out.println("|" + salidaParticipante  +   salidaPuntos +  salidaRonda  + salidaFase + salidaTotal + salidaCierre );
                 System.out.println("-----------------------------------------------------------------------------------------------------------");
             
}
}
