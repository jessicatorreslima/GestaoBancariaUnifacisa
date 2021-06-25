package com.unifacisa.banco.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConta;
	
	@ManyToOne
	@JoinColumn(name="idPessoa")
	private Pessoa idPessoa;
	
	private double saldo;
	private double limiteSaqueDiario;
	private boolean flagAtivo;
	private TipoConta tipoConta;
	private LocalDate dataCriacao;
	
	public Long getIdConta() {
		return idConta;
	}
	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
	public Pessoa getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Pessoa idPessoa) {
		this.idPessoa = idPessoa;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public double getLimiteSaqueDiario() {
		return limiteSaqueDiario;
	}
	public void setLimiteSaqueDiario(double limiteSaqueDiario) {
		this.limiteSaqueDiario = limiteSaqueDiario;
	}
	public boolean isFlagAtivo() {
		return flagAtivo;
	}
	public void setFlagAtivo(boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}
	public TipoConta getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}
