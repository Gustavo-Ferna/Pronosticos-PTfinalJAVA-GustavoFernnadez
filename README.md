# Argentina Programa 4.0 - Desarrollador Java Inicial
## Comisión 144 
|Alumno: Julio Shaya|
| :-------: |

 

## Trabajo Práctico Integrador (Pronósticos Deportivos)

## Consta de 3 Entregas 

## Entrega 1

## Alcance:
En este trabajo práctico nos limitaremos a pronosticar los resultados de los partidos, sin importar los goles ni la estructura del torneo (si es grupo, eliminatoria u otro); simplemente se sumarán puntos y se obtendrá un listado final con los puntos obtenidos por el participante.

## Observaciones 
Enviar mediante parámetros (resultados.csv,pronosticos.csv) con sus rutas correspondientes y delimitados por "," respetando los formatos abajo detallados).

#### Estructura del archivo resultados.csv

|  Equipo1 | Cant. Goles 1 | Cant. Goles 3 |Equipo2|
| :------: | :----: | :----: | :-----: |


#### Estructura del archivo pronostico.csv:
|  Equipo1 | Gana 1 | Empata | Gana 2  |Equipo2|
| :------: | :----: | :----: | :-----: | :---: |

## Entrega 2 

## Alcance:
En esta entrega los archivos de entrada difieren de la primera agregando datos de Ronda al archivo resultados.csv y Persona al archivo de pronostico.csv.
Se debe calcular los aciertos para cada persona arrojando por pantalla el nombre del participante con la cantidad de puntos obtenidos.
Ejemplo:

Pedro: 3 puntos

Juan: 5 puntos


#### Estructura del archivo resultados.csv

|Ronda |  Equipo1 | Cant. Goles 1 | Cant. Goles 3 |Equipo2|
|:---: |  :------:| :----------:  | :----: | :-----: |


#### Estructura del archivo pronostico.csv:
| Participante |   Equipo1 | Gana 1 | Empata | Gana 2  |Equipo2|
| :------: | :------: | :----: | :----: | :-----: | :---: |


## Entrega 3 

## Alcance:
En esta entrega se deben poder leer los pronósticos desde una base de datos MySQL. Por
otro lado, debe poder ser configurable la cantidad de puntos que se otorgan cuando se acierta
un resultado (ganar, perder, empatar).
Finalmente, se agregan 2(dos) reglas para la asignación de puntajes de los participantes:

Se suman puntos extra cuando se aciertan todos los resultados de una ronda.
Se suman puntos extra cuando se aciertan todos los resultados de una fase
Se debe considerar que una fase es un conjunto de rondas.

#### Estructura del archivo resultados.csv

|Ronda |  Equipo1 | Cant. Goles 1 | Cant. Goles 3 |Equipo2 | Fase |
|:---: |  :------:| :----------:  | :----: | :-----: |  :-----: |


Aquí se agregó una clase Ronda, clase Fase y también una clase conexionBD, como parámetro se agregó un archivo configuración.txt con la siguiente estructura:

#### bd=tp (Nombre de la Base de Datos)
#### url=jdbc:mysql://localhost:3306/  (Servidor donde se encuentra la Base de Datos MySql)
#### user=root (Usuario)
#### password= (Contraseña conexión base de datos)
#### driver=com.mysql.cj.jdbc.Driver (driver utilizado)
#### PuntosGanador=1 (Puntos por aciertos)
#### PuntosExtraRonda=2 (Puntos Extra si se acierta todos los partidos de la Ronda)
#### PuntosExtraFase=3 (Puntos Extra si se acierta todos los partidos una Fase)

## Resultado Final: 

| Participante | Aciertos | Puntos X Ronda |  Puntos X Fase   | Total de Puntos|
| :------: | :------: | :----: | :----: |  :----: |  











