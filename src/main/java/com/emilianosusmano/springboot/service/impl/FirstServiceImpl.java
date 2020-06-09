package com.emilianosusmano.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emilianosusmano.springboot.bo.CadenaAdnBo;
import com.emilianosusmano.springboot.bo.StatsBo;
import com.emilianosusmano.springboot.dao.EstadisticaDao;
//import com.emilianosusmano.springboot.dao.EstadisticaDao;
import com.emilianosusmano.springboot.dto.CadenaAdnDto;
import com.emilianosusmano.springboot.dto.StatsDto;
import com.emilianosusmano.springboot.entities.EstadisticaMutante;
import com.emilianosusmano.springboot.responses.ResponseMutante;
import com.emilianosusmano.springboot.service.FirstService;
import com.emilianosusmano.springboot.service.VerificadorMutanteService;

@Service
public class FirstServiceImpl implements FirstService {
	@Autowired
	VerificadorMutanteService verificadorMutanteService;
	@Autowired
	EstadisticaDao estadisticaDao;

	@Override
	public ResponseMutante validarMutante(CadenaAdnDto adn) {
		ResponseMutante response = new ResponseMutante();
		CadenaAdnBo adnBo = new CadenaAdnBo().build(adn);
		if (verificadorMutanteService.cadenaValida(adnBo)) {
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
				response.setEsMutante("ERROR al guardar Entidad en Base de Datos.");
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
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
	public List<EstadisticaMutante> obtenerDatosEstadistica() {
		return (List<EstadisticaMutante>) estadisticaDao.findAll();
	}
}
