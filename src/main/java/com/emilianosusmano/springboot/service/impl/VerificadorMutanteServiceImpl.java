package com.emilianosusmano.springboot.service.impl;

import org.springframework.stereotype.Service;

import com.emilianosusmano.springboot.service.VerificadorMutanteService;

@Service
public class VerificadorMutanteServiceImpl implements VerificadorMutanteService {

	@Override
	public Boolean isMutant(String[] adn) {
		char[][] matriz = pasarCadenaAMatriz(adn);
		return resultadoBusquedaSecuenciaMutante(matriz);
	}

	@Override
	public Boolean cadenaValida(String[] dna) {
		for (String str : dna) {
			str = str.toUpperCase();
			if (dna.length != str.length() || dna.length < 4) {
				System.out.println("[CADENA INVALIDA] - ERROR de Ingreso de Datos. Longitud de Cadena Invalida.");
				return false;
			}
			if (str.matches(".*[BDEFHIJKLMN�OPQRSUVWXYZ0123456789].*")) {
				System.out.println("[CADENA INVALIDA] - ERROR de Ingreso de Datos. Caracter Invalido.");
				return false;
			}
		}

		System.out.println("[CADENA VALIDA] - Continuo con carga de datos.");
		return true;
	}

	private char[][] pasarCadenaAMatriz(String[] dna) {
		char[][] matriz = new char[dna.length][dna.length];

		for (int i = 0; i < dna.length; i++) {
			for (int j = 0; j < dna.length; j++) {
				matriz[i][j] = dna[i].charAt(j);
			}
		}

		return matriz;
	}
	
	@Override
	public String convertirArrayAString(String[] array) {
		String cadena = "";
		for (String str : array) {
			cadena = cadena.concat(str);
		}
		return cadena;
	}

	private Boolean resultadoBusquedaSecuenciaMutante(char[][] matriz) {
		int contadorResultados = 0;

		contadorResultados = contadorResultados + buscarSecuencia(matriz);
		if (contadorResultados > 1) {
			return true;
		}

		contadorResultados = contadorResultados + buscarSecuencia(transponerMatriz(matriz));
		if (contadorResultados > 1) {
			return true;
		}
		contadorResultados = contadorResultados + buscarOblicuosPrincipales(matriz);
		if (contadorResultados > 1) {
			return true;
		}
		return contadorResultados > 1;
	}

	private int buscarSecuencia(char[][] matriz) {
		int contCharsConsecutivos;
		int contSecuenciasEncontradas = 0;

		for (int i = 0; i < matriz.length; i++) {
			contCharsConsecutivos = 1;
			for (int j = 1; j < matriz[i].length; j++) {
				if (matriz[i][j] == matriz[i][j - 1]) {
					contCharsConsecutivos = contCharsConsecutivos + 1;
					if (contCharsConsecutivos >= 4) {
						contSecuenciasEncontradas = contSecuenciasEncontradas + 1;
					}
				} else {
					contCharsConsecutivos = 1;
				}
			}
		}
		return contSecuenciasEncontradas;
	}

	private char[][] transponerMatriz(char[][] matriz) {
		char[][] result = matriz.clone();
		char aux;
		for (int x = 0; x < result.length; x++) {
			for (int y = 0; y < result[x].length; y++) {
				if (x > y) {
					aux = result[x][y];
					result[x][y] = result[y][x];
					result[y][x] = aux;
				}
			}
		}
		return result;
	}

	private int buscarOblicuosPrincipales(char[][] matriz) {
		int contCharsConsecutivos;
		int contSecuenciasEncontradas = 0;

		for (int i = 0; i < matriz.length - 3; i++) {
			for (int j = 0; j < matriz[i].length - 3; j++) {
				contCharsConsecutivos = 1;
				int x = i;
				int y = j;
				while (x < matriz.length - 3 && y < matriz[i].length - 3 && matriz[x][y] == matriz[x + 1][y + 1]) {
					if (matriz[x][y] == matriz[x + 1][y + 1]) {
						contCharsConsecutivos = contCharsConsecutivos + 1;
						if (contCharsConsecutivos >= 4) {
							contSecuenciasEncontradas = contSecuenciasEncontradas + 1;
						}
					} else {
						contCharsConsecutivos = 1;
					}
					x++;
					y++;
				}
			}
		}
		return contSecuenciasEncontradas;
	}

}
