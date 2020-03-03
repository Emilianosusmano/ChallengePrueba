# ChallengeMutant

# Instrucciones de Uso:

*Esta aplicacion es utilizada por Magneto para reconocer el Adn de las personas, tanto humanas como mutantes, para asi, seleccionar a los mutantes para que pertenezcan a su equipo.

*Mediante la aplicacion Postman, o similar, se debera enviar una request de POST a la URL: https://challenge-mutantes.cfapps.io/ApiREST/mutant , en esta request se debera enviar en formato JSon una cadena de ADN conformada por [NxN], osea, la cadena debe tener tantos elementos como caracteres tenga cada uno de sus elementos; 
por ejemplo la Cadena:
{"dna" : ["AAAA", "CCCC", "TTTT", "GGGG"]}  

Sera aceptada como una cadena valida, la cual sera posible analizar para ver si es ADN Humano o Mutante, en caso de ser Mutante devuelve un HTTP 200 - Ok, en caso de ser Humano devuelve un HTTP 403 - Forbidden.

Pero las cadenas: 
 {"dna" : ["AAA", "CCC"]} ó {"dna" : ["AAA", "GGG", "OO"]}
 
Seran rechazadas como cadenas invalidas, devolviendo un HTTP 400 - Bad Request.
  
-Nota: Las cadenas de ADN solo pueden estar conformadas por las bases nitrogenadas A C G T y por lo menos deben ser de [4x4] para poder comprobar si son Mutantes o Humanas, por lo que si en la cadena se encuentra alguna otra base nitrogenada, o menores a [4x4]  sera invalida y tambien se devolvera un HTTP 400 - Bad Request.

*Una vez que se hayan Analizado las muestras de ADN necesarias, mediante la aplicacion Postman, o similares, se puede enviar una request de GET a la URL: https://challenge-mutantes.cfapps.io/ApiREST/stats , esta request devolvera una estadistica de la cantidad de Mutantes, la cantidad de Humanos y que ratio de mutantes hay sobre la cantidad total de muestras analizadas.

-------------------------------------------------------------------------------------------------------------------

*La aplicacion esta basada en Java, usando un Framework SpringBoot para poder manejar los datos transportandolos en JSon. Utilizo Repositorios Crud para poder cargar los datos en una base de datos MySQL online en Pivotal CloudFoundry y los test unitarios estan realizados con JUnit.

# Emiliano Susmano.
