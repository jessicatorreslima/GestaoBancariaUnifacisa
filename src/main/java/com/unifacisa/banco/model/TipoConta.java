package com.unifacisa.banco.model;

public enum TipoConta {
	CORRENTE(1),
	POUPANCA(2),
	SALARIO(3);
	
	private int valor;

	private TipoConta(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
	
}
