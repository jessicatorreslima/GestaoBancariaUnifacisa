package com.unifacisa.banco.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.unifacisa.banco.model.Conta;
import com.unifacisa.banco.model.Transacao;
import com.unifacisa.banco.repository.ContaRepository;
import com.unifacisa.banco.repository.TransacaoRepository;

import jdk.jfr.Timestamp;


@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@GetMapping
	public List<Conta> listar() {
		return contaRepository.findAll();
	}
	
	@GetMapping("/{idConta}")
	public Optional<Conta> consultar(@PathVariable int idConta) {
		return contaRepository.findById(idConta);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Conta adicionar(@RequestBody Conta conta) {
		return contaRepository.save(conta);
	}
	
	@PutMapping("/{idConta}")
	public Conta atualizar(@RequestBody Conta conta) {
		return contaRepository.save(conta);
	}
	
	@GetMapping("/saldo/{idConta}")
	public BigDecimal consultarSaldo(@PathVariable int idConta) {
		return contaRepository.findSaldoById(idConta);
	}
	
	@PutMapping("/bloqueio/{idConta}")
	public int bloquear(@PathVariable int idConta) {
		return contaRepository.bloqueiaById(idConta);
	}
	
	@PutMapping("/deposito")
	public int depositar(@RequestParam int idConta, @RequestParam BigDecimal valor) {
		Conta conta = contaRepository.findById(idConta).get();
		BigDecimal novoSaldo = conta.getSaldo().add(valor);
		Transacao transacao = new Transacao(conta,valor);
		transacaoRepository.save(transacao);
		return contaRepository.changeBalanceById(idConta,novoSaldo);
	}	
		
	@PutMapping("/saque")
	public int sacar(@RequestParam int idConta, @RequestParam BigDecimal valor) {
		Conta conta = contaRepository.findById(idConta).get();
		BigDecimal novoSaldo = conta.getSaldo();
		if (conta.getSaldo().subtract(valor).compareTo(new BigDecimal("0")) >= 0)
		{
			novoSaldo = conta.getSaldo().subtract(valor);
			Transacao transacao = new Transacao(conta,valor.negate());
			transacaoRepository.save(transacao);
		} 
		return contaRepository.changeBalanceById(idConta,novoSaldo);
	}
	
	@GetMapping("/extrato/{idConta}")
	public List<Transacao> consultarExtrato(@PathVariable int idConta) {
		return transacaoRepository.findByConta(contaRepository.findById(idConta));
	}
	
	@GetMapping("/extrato/periodo")
	public List<Transacao> consultarExtratoPorPeriodo(@RequestParam int idConta, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
		return transacaoRepository.findByContaAndDataTransacaoGreaterThanAndDataTransacaoLessThan(contaRepository.findById(idConta), dataInicial, dataFinal);
	}
	public ContaController() {
		// TODO Auto-generated constructor stub
	}
}
