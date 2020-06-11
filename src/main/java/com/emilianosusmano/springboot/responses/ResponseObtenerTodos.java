package com.emilianosusmano.springboot.responses;

import java.util.ArrayList;
import java.util.List;

import com.emilianosusmano.springboot.entities.EstadisticaMutante;

public class ResponseObtenerTodos {
	List<EstadisticaMutante> listaTodos = new ArrayList<EstadisticaMutante>();

	public List<EstadisticaMutante> getListaTodos() {
		return listaTodos;
	}

	public void setListaTodos(List<EstadisticaMutante> listaTodos) {
		this.listaTodos = listaTodos;
	}

}
