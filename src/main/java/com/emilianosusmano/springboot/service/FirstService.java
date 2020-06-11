package com.emilianosusmano.springboot.service;

import com.emilianosusmano.springboot.dto.CadenaAdnDto;
import com.emilianosusmano.springboot.dto.EnviarMailDTO;
import com.emilianosusmano.springboot.dto.StatsDto;
import com.emilianosusmano.springboot.responses.ResponseMutante;
import com.emilianosusmano.springboot.responses.ResponseObtenerTodos;

public interface FirstService {

	ResponseMutante validarMutante(CadenaAdnDto adn);

	StatsDto obtenerEstadisticas();

	ResponseObtenerTodos obtenerDatosEstadistica();
	
	String enviarMailEstadisticas(EnviarMailDTO mail);

}
