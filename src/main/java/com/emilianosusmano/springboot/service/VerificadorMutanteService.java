package com.emilianosusmano.springboot.service;

public interface VerificadorMutanteService {

	Boolean isMutant(String[] adn);

	Boolean cadenaValida(String[] dna);

	String convertirArrayAString(String[] array);
}
