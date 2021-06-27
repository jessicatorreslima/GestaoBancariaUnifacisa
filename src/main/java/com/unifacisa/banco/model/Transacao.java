package com.unifacisa.banco.model;

import java.math.BigDecimal;
import java.time.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="TB_TRANSACAO")
public class Transacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTransacao;
	
	@ManyToOne
	@JoinColumn(name = "idConta")
	private Conta conta;
	
	@CreationTimestamp
	private LocalDateTime dataTransacao;
	
	private BigDecimal valor;

	public int getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(int idTransacao) {
		this.idTransacao = idTransacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataTransacao() {
		return dataTransacao;
	}
	
	Transacao(){
		
	}
	Transacao(int idTransacao, Conta conta, LocalDateTime dataTransacao, BigDecimal valor) {
		super();
		this.idTransacao = idTransacao;
		this.conta = conta;
		this.dataTransacao = dataTransacao;
		this.valor = valor;
	}
	
	
	
}
