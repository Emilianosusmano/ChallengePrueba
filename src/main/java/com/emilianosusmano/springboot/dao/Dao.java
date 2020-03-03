package com.emilianosusmano.springboot.dao;

import java.util.List;

import com.emilianosusmano.springboot.entities.EstadisticaMutante;
import com.emilianosusmano.springboot.entities.EstadisticaMutanteHibernate;

public interface Dao {

	Long createAndStorePerson(EstadisticaMutante em);

	List<EstadisticaMutanteHibernate> listPersons();

}
