package com.emilianosusmano.springboot.bo;

import com.emilianosusmano.springboot.dto.CadenaAdnDto;

public class CadenaAdnBo {
	private String dna;
	private Integer cantCadenas;

	public CadenaAdnBo build(CadenaAdnDto adn) {
		this.dna = adn.getDna().toUpperCase().replace(" ", "").replace(",", "").replace(".", "");
		Double cant = Math.sqrt(this.dna.length());
		if (cant % 1 == 0) {
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
