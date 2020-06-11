package com.emilianosusmano.springboot.service.impl;

import org.springframework.stereotype.Service;

import com.emilianosusmano.springboot.bo.CadenaAdnBo;
import com.emilianosusmano.springboot.service.VerificadorMutanteService;

@Service
public class VerificadorMutanteServiceImpl implements VerificadorMutanteService {

	@Override
	public Boolean isMutant(CadenaAdnBo bo) {
		String[] adnAux = pasarAArregloDeString(bo);
		char[][] matriz = pasarCadenaAMatriz(adnAux);
		return resultadoBusquedaSecuenciaMutante(matriz);
	}

	@Override
	public String cadenaValida(CadenaAdnBo bo) {
		String mensajeError = "ERROR de Ingreso de Datos, pruebe ingresando solo las Bases Nitrogenadas (A, C, T, G). "
				+ "Recuerde que la cantidad de bases nitrogenadas debe ser de NxN con un N mayor o igual a 4";
		if (bo.getCantCadenas() != null && bo.getCantCadenas() != 0) {
			String[] dna = pasarAArregloDeString(bo);
			for (String str : dna) {
				if (dna.length != str.length() || dna.length < 4) {
					return "Longitud de Cadena Invalida. " + mensajeError;
				}
				if (str.matches(".*[BDEFHIJKLMN�OPQRSUVWXYZ0123456789].*")) {
					return "Caracter Invalido. " + mensajeError;
				}
			}
		} else {
			return mensajeError;
		}
		return "VALIDA";
	}

	private String[] pasarAArregloDeString(CadenaAdnBo bo) {
		String[] adnChain = new String[bo.getCantCadenas()];
		int endString = 0;
		for (int i = 0; i < bo.getCantCadenas(); i++) {
			adnChain[i] = bo.getDna().substring(i * bo.getCantCadenas(), bo.getCantCadenas() + endString);
			endString = endString + bo.getCantCadenas();
		}
		return adnChain;
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
