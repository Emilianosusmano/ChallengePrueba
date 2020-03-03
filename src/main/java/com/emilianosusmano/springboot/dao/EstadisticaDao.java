package com.emilianosusmano.springboot.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emilianosusmano.springboot.entities.EstadisticaMutante;

@Repository
public interface EstadisticaDao extends CrudRepository<EstadisticaMutante, Integer> {
	@Query("SELECT COUNT(*) FROM EstadisticaMutante where is_mutant = true")
	int countMutants();

	@Query("SELECT COUNT(*) FROM EstadisticaMutante where is_mutant = false")
	int countNotMutants();

	@Query("SELECT COUNT(*) FROM EstadisticaMutante")
	int countAll();
}
