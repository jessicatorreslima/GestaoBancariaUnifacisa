package com.unifacisa.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping
	@ResponseStatus
	public Conta adicionar(@RequestBody Conta conta) {
		return contaRepository.save(conta);
	}

}
