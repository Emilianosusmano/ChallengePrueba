package com.emilianosusmano.springboot.bo;

import com.emilianosusmano.springboot.dto.CadenaAdnDto;

public class CadenaAdnBo {
	private String[] dna;
	
	public CadenaAdnBo build(CadenaAdnDto adn) {
		this.dna = adn.getDna();
		return this;
	}

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}
}
