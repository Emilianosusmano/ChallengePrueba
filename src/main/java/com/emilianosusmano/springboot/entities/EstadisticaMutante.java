package com.emilianosusmano.springboot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EstadisticaMutante", schema = "public")
public class EstadisticaMutante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPersona;

	@Column(name = "dna")
	private String dna;

	@Column(name = "isMutant")
	private Boolean isMutant;

	@Column(name = "cantCadenas")
	private int cantCadenas;

	public int getCantCadenas() {
		return cantCadenas;
	}

	public void setCantCadenas(int cantCadenas) {
		this.cantCadenas = cantCadenas;
	}

	public EstadisticaMutante() {
		super();
		
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
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
}
