package com.unifacisa.banco.controller;

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
import com.unifacisa.banco.repository.TransacaoRepository;

/**
 * @author <a href="malito:jtlimapro@gmail.com">Jéssica Torres de Lima</a>
 *
 */
@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	/**
	 * Lista todas as transacoes.<br>
	 * Método: GET<br>
	 * Path: /transacoes<br>
	 * @return	uma lista de <code>Transacao</code>
	 */
	@GetMapping
	public List<Transacao> listar() {
		return transacaoRepository.findAll();
	}
	
	/**
	 * Busca uma transação pelo idTransacao.<br>
	 * Método: GET<br>
	 * Path: /transacoes/1<br>
	 * @return	a transação que possui o idTransacao informado, caso exista
	 */
	@GetMapping("/{idTransacao}")
	public Optional<Transacao> consultar(@PathVariable int idTransacao) {
		return transacaoRepository.findById(idTransacao);
	}
		
	/**
	 * Cadastra uma nova transação.<br>
	 * Método: POST<br>
	 * Path: /transacoes<br>
	 * Para ser utilizado apenas por <code>sacar</code> e <code>depositar</code> de <code>ContaController</code>
	 * @param	transacao objeto Transacao a ser criada
	 * @return	a transacao criada
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Transacao adicionar(@RequestBody Transacao transacao) {
		return transacaoRepository.save(transacao);
	}
	
	public TransacaoController() {
		// TODO Auto-generated constructor stub
	}

}
