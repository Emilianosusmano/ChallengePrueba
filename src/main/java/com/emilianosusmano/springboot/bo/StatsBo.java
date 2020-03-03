package com.emilianosusmano.springboot.bo;

public class StatsBo {
	private int count_mutant_dna;
	private int count_human_dna;
	private double ratio;

	public StatsBo build(int dnaMutante, int dnaHumano, int total) {
		this.count_mutant_dna = dnaMutante;
		this.count_human_dna = dnaHumano;
		if (dnaMutante > 0 && dnaHumano > 0) {
			double aux = (double) dnaMutante / total;
			this.ratio = Math.round(aux * 100) / 100d;
		} else {
			this.ratio = 0;
		}

		return this;
	}

	public int getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public void setCount_mutant_dna(int count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}

	public int getCount_human_dna() {
		return count_human_dna;
	}

	public void setCount_human_dna(int count_human_dna) {
		this.count_human_dna = count_human_dna;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
}
