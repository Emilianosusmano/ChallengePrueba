package com.emilianosusmano.springboot.service;

import com.emilianosusmano.springboot.dto.CadenaAdnDto;
import com.emilianosusmano.springboot.dto.StatsDto;
import com.emilianosusmano.springboot.responses.ResponseMutante;

public interface FirstService {
	
	ResponseMutante validarMutante(CadenaAdnDto adn);
	
//	StatsDto obtenerEstadisticas();
		
}
