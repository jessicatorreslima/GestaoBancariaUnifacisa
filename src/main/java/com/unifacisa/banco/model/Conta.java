package com.unifacisa.banco.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="TB_CONTA")
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idConta;
	
	@ManyToOne
	@JoinColumn(/*nullable = false,*/ name = "idPessoa")
	private Pessoa idPessoa;
	
	//private int idPessoa;
	private BigDecimal saldo = new BigDecimal(0.00);
	private BigDecimal limiteSaqueDiario;
	private boolean flagAtivo;
	private TipoConta tipoConta;
	
	@CreationTimestamp
	private LocalDate dataCriacao;
	
	public int getIdConta() {
		return idConta;
	}
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public BigDecimal getLimiteSaqueDiario() {
		return limiteSaqueDiario;
	}
	public void setLimiteSaqueDiario(BigDecimal limiteSaqueDiario) {
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
}
