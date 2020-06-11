package com.emilianosusmano.springboot.service;

import com.emilianosusmano.springboot.bo.CadenaAdnBo;

public interface VerificadorMutanteService {

	Boolean isMutant(CadenaAdnBo bo);

	String cadenaValida(CadenaAdnBo adnBo);

	String convertirArrayAString(String[] array);
}
