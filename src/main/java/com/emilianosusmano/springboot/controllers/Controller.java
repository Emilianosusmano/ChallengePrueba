package com.emilianosusmano.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.emilianosusmano.springboot.dto.CadenaAdnDto;
import com.emilianosusmano.springboot.responses.ResponseMutante;
import com.emilianosusmano.springboot.responses.ResponseObject;
import com.emilianosusmano.springboot.responses.ResponseStats;
import com.emilianosusmano.springboot.service.FirstService;

@RestController
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
		}

		return response.toStatus400BadRequest();
	}

	@RequestMapping(value = "/stats", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseObject<ResponseStats> stats(ResponseObject<ResponseStats> response) {
		ResponseStats result = new ResponseStats().build(firstService.obtenerEstadisticas());
		response.setResult(result);

		return response.toStatus200OK();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/redirect")
	public ModelAndView redirigirGoogle() {
		return new ModelAndView("redirect:http://www.google.com.ar");
	}

}