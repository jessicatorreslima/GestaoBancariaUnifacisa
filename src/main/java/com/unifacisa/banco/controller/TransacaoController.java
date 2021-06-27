package com.unifacisa.banco.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.unifacisa.banco.model.Transacao;
import com.unifacisa.banco.repository.TransacaoRepository;

public class TransacaoController {
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@GetMapping
	public List<Transacao> listar() {
		return transacaoRepository.findAll();
	}
	
	@GetMapping("/{idConta}")
	public Optional<Transacao> consultar(@PathVariable int idTransacao) {
		return transacaoRepository.findById(idTransacao);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Transacao adicionar(@RequestBody Transacao transacao) {
		return transacaoRepository.save(transacao);
	}

}
