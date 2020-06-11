package com.emilianosusmano.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emilianosusmano.springboot.bo.CadenaAdnBo;
import com.emilianosusmano.springboot.bo.StatsBo;
import com.emilianosusmano.springboot.dao.EstadisticaDao;
//import com.emilianosusmano.springboot.dao.EstadisticaDao;
import com.emilianosusmano.springboot.dto.CadenaAdnDto;
import com.emilianosusmano.springboot.dto.EnviarMailDTO;
import com.emilianosusmano.springboot.dto.StatsDto;
import com.emilianosusmano.springboot.entities.EstadisticaMutante;
import com.emilianosusmano.springboot.responses.ResponseMutante;
import com.emilianosusmano.springboot.responses.ResponseObtenerTodos;
import com.emilianosusmano.springboot.service.FirstService;
import com.emilianosusmano.springboot.service.MailService;
import com.emilianosusmano.springboot.service.VerificadorMutanteService;

@Service
public class FirstServiceImpl implements FirstService {
	@Autowired
	VerificadorMutanteService verificadorMutanteService;
	@Autowired
	EstadisticaDao estadisticaDao;
	@Autowired
	MailService mailService;
	@Autowired
	Environment env;
	
	@Override
	public ResponseMutante validarMutante(CadenaAdnDto adn) {
		CadenaAdnBo adnBo = new CadenaAdnBo().build(adn);
		ResponseMutante response = validacionMutante(adnBo);
		return response;
	}
	
	private ResponseMutante validacionMutante(CadenaAdnBo adnBo) {
		ResponseMutante response = new ResponseMutante();
		String cadenaValida = verificadorMutanteService.cadenaValida(adnBo);
		if (cadenaValida.equalsIgnoreCase("VALIDA")) {
			EstadisticaMutante em = new EstadisticaMutante();
			em.setIsMutant(verificadorMutanteService.isMutant(adnBo));
			em.setDna(adnBo.getDna());
			em.setCantCadenas(adnBo.getCantCadenas());
			if (verificadorMutanteService.isMutant(adnBo)) {
				response.setEsMutante("MUTANTE Agregado a Estadistica.");
				response.setStatus(HttpStatus.OK);
			} else {
				response.setEsMutante("HUMANO Agregado a Estadistica.");
				response.setStatus(HttpStatus.FORBIDDEN);
			}
			try {
				estadisticaDao.save(em);
			} catch (Exception e) {
				response.setEsMutante("Error en la persitencia de datos.");
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				return response;
			}
		} else {
			response.setEsMutante(cadenaValida);
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@Override
	public StatsDto obtenerEstadisticas() {
		StatsBo statsBo = new StatsBo().build(estadisticaDao.countMutants(), estadisticaDao.countNotMutants(),
				estadisticaDao.countAll());
		StatsDto statsDto = new StatsDto().build(statsBo);
		return statsDto;
	}

	@Override
	public ResponseObtenerTodos obtenerDatosEstadistica() {
		ResponseObtenerTodos response = new ResponseObtenerTodos();
		response.setListaTodos((List<EstadisticaMutante>) estadisticaDao.findAll());
		return response;
	}

	@Override
	public String enviarMailEstadisticas(EnviarMailDTO mail) {
		StatsDto stats = this.obtenerEstadisticas();
		try {
			mailService.sendEmailEstadisticas(mail.getEmail(), stats);
		} catch (Exception e) {
			return "Error al enviar Mail";
		}
		return "Mail enviado Correctamente.";
	}
}
