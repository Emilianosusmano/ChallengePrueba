package com.emilianosusmano.springboot.responses;

import com.emilianosusmano.springboot.dto.StatsDto;

public class ResponseStats {
	private int contadorDnaMutante;
	private int contadorDnaHumano;
	private double ratio;

	public ResponseStats build(StatsDto dto) {
		this.contadorDnaMutante = dto.getContadorDnaMutante();
		this.contadorDnaHumano = dto.getContadorDnaHumano();
		this.ratio = dto.getRatio();
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
