package com.emilianosusmano.springboot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EstadisticaMutanteHibernate {
	@Id
	@GeneratedValue
	private Long idPersona;
	private String dna;
	private Boolean isMutant;
	private int cantCadenas;

	public EstadisticaMutanteHibernate() {
		super();
	}

	public int getCantCadenas() {
		return cantCadenas;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public Boolean getIsMutant() {
		return isMutant;
	}

	public void setIsMutant(Boolean isMutant) {
		this.isMutant = isMutant;
	}

	public void setCantCadenas(int cantCadenas) {
		this.cantCadenas = cantCadenas;
	}

}