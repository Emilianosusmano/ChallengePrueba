package com.emilianosusmano.springboot.bo;

import com.emilianosusmano.springboot.dto.CadenaAdnDto;

public class CadenaAdnBo {
	private String dna;
	private Integer cantCadenas;

	public CadenaAdnBo build(CadenaAdnDto adn) {
		String aux = adn.getDna().toString().trim().toUpperCase();
		Double cant = Math.sqrt(aux.length());
		if (cant % 1 == 0) {
			this.dna = aux;
			this.cantCadenas = cant.intValue();
		}
		return this;
	}

	public String getDna() {
		return dna;
	}
	public void setDna(String dna) {
		this.dna = dna;
	}
	public Integer getCantCadenas() {
		return cantCadenas;
	}
	public void setCantCadenas(Integer cantCadenas) {
		this.cantCadenas = cantCadenas;
	}
}
