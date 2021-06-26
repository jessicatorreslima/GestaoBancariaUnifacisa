package com.unifacisa.banco.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.unifacisa.banco.model.Conta;
import com.unifacisa.banco.repository.ContaRepository;


@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
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
	//TODO
	@PutMapping("/bloqueio/{idConta}")
	public Conta bloquear(@PathVariable int idConta) {
		return contaRepository.bloqueiaById(idConta);
	}
}
