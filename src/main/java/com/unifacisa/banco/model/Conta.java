package com.unifacisa.banco.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="TB_CONTA")
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idConta;
	
	/*@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn
	private Pessoa idPessoa;*/
	
	private int idPessoa;
	
	private BigDecimal saldo = new BigDecimal(0.00);
	
	private BigDecimal limiteSaqueDiario;
	
	private boolean flagAtivo;
	
	//private TipoConta tipoConta;
	private int tipoConta;
	
	@CreationTimestamp
	private LocalDate dataCriacao;
	
	@OneToMany
    private List<Transacao> Extrato;
	
	public int getIdConta() {
		return idConta;
	}
	
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	
	public int getIdPessoa() {
		return idPessoa;
	}
	
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
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
	
	public int getTipoConta() {
		return tipoConta;
	}
	
	public void setTipoConta(int tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}	
	
	public List<Transacao> getExtrato() {
		return Extrato;
	}
	
	public void addTransacao(Transacao transacao) {
		Extrato.add(transacao);
    }
	
	public Conta() {
		super();
	}
	
	public Conta(int idConta, int idPessoa, BigDecimal saldo, BigDecimal limiteSaqueDiario, boolean flagAtivo,
			int tipoConta, LocalDate dataCriacao, List<Transacao> extrato) {
		super();
		this.idConta = idConta;
		this.idPessoa = idPessoa;
		this.saldo = saldo;
		this.limiteSaqueDiario = limiteSaqueDiario;
		this.flagAtivo = flagAtivo;
		this.tipoConta = tipoConta;
		this.dataCriacao = dataCriacao;
		Extrato = extrato;
	}		
}
