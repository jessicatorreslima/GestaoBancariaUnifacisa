package com.unifacisa.banco.controller;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.unifacisa.banco.model.Transacao;
import com.unifacisa.banco.repository.ContaRepository;
import com.unifacisa.banco.repository.TransacaoRepository;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping
	public List<Transacao> listar() {
		return transacaoRepository.findAll();
	}
	
	/*@GetMapping("/{idTransacao}")
	public Optional<Transacao> consultar(@PathVariable int idTransacao) {
		return transacaoRepository.findById(idTransacao);
	}*/
	
	@GetMapping("/valor/{idConta}")
	public BigDecimal consulta(@PathVariable int idConta) {
		return transacaoRepository.findValorById(idConta);
	}
	
	/*@GetMapping("/conta/{idConta}")
	public List<Transacao> listarFiltrado(@PathVariable int idConta) {
		return transacaoRepository.findByConta(contaRepository.findById(idConta));
	}*/
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Transacao adicionar(@RequestBody Transacao transacao) {
		return transacaoRepository.save(transacao);
	}

}
