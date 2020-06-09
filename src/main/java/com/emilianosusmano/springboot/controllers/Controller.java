package com.emilianosusmano.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emilianosusmano.springboot.dto.CadenaAdnDto;
import com.emilianosusmano.springboot.entities.EstadisticaMutante;
import com.emilianosusmano.springboot.responses.ResponseMutante;
import com.emilianosusmano.springboot.responses.ResponseObject;
import com.emilianosusmano.springboot.responses.ResponseStats;
import com.emilianosusmano.springboot.service.FirstService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/challenge")
public class Controller {
	@Autowired
	FirstService firstService;

	@RequestMapping(method = RequestMethod.POST, path = "/mutant", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseObject<ResponseMutante> validarMutante(@RequestBody CadenaAdnDto adn,
			ResponseObject<ResponseMutante> response) {
		ResponseMutante result = firstService.validarMutante(adn);
		response.setResult(result);

		if (result.getStatus().equals(HttpStatus.OK)) {
			return response.toStatus200OK();
		} else if (result.getStatus().equals(HttpStatus.FORBIDDEN)) {
			return response.toStatus403Forbidden();
		} else if (result.getStatus().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
			return response.toStatus500InternalServerError();
		}

		return response.toStatus400BadRequest();
	}

	@RequestMapping(value = "/stats", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseObject<ResponseStats> stats(ResponseObject<ResponseStats> response) {
		response.setResult(new ResponseStats().build(firstService.obtenerEstadisticas()));
		return response.toStatus200OK();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/obtenerTodos")
	public ResponseObject<List<EstadisticaMutante>> obtenerTodos(ResponseObject<List<EstadisticaMutante>> response) {
		response.setResult(firstService.obtenerDatosEstadistica());
		return response.toStatus200OK();
	}

}