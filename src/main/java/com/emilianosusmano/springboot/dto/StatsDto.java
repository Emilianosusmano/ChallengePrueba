package com.emilianosusmano.springboot.dto;

import com.emilianosusmano.springboot.bo.StatsBo;

public class StatsDto {
	private int contadorDnaMutante;
	private int contadorDnaHumano;
	private double ratio;

	public StatsDto build(StatsBo bo) {
		this.contadorDnaMutante = bo.getCount_mutant_dna();
		this.contadorDnaHumano = bo.getCount_human_dna();
		this.ratio = bo.getRatio();
		return this;
	}

	public int getContadorDnaMutante() {
		return contadorDnaMutante;
	}

	public void setContadorDnaMutante(int contadorDnaMutante) {
		this.contadorDnaMutante = contadorDnaMutante;
	}

	public int getContadorDnaHumano() {
		return contadorDnaHumano;
	}

	public void setContadorDnaHumano(int contadorDnaHumano) {
		this.contadorDnaHumano = contadorDnaHumano;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

}
