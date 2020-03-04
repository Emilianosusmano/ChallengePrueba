package com.emilianosusmano.springboot.service;

import java.util.List;

import com.emilianosusmano.springboot.dto.CadenaAdnDto;
import com.emilianosusmano.springboot.dto.StatsDto;
import com.emilianosusmano.springboot.entities.EstadisticaMutante;
import com.emilianosusmano.springboot.responses.ResponseMutante;

public interface FirstService {
	
	ResponseMutante validarMutante(CadenaAdnDto adn);
	
	StatsDto obtenerEstadisticas();
	
	List<EstadisticaMutante> obtenerDatosEstadistica();
		
}
