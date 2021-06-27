package com.unifacisa.banco.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.unifacisa.banco.model.Pessoa;
import com.unifacisa.banco.repository.PessoaRepository;

/**
 * @author <a href="malito:jtlimapro@gmail.com">Jéssica Torres de Lima</a>
 *
 */
@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	/**
	 * Lista todas as pessoas cadastradas.<br>
	 * Método: GET<br>
	 * Path: /pessoas<br>
	 * @return	uma lista de <code>Pessoa</code>
	 */
	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	/**
	 * Busca uma pessoa pelo idPessoa.<br>
	 * Método: GET<br>
	 * Exemplo do path: /pessoas/1<br>
	 * 
	 * @param	idPessoa da pessoa a ser buscada
	 * @return	a pessoa que possui o idPessoa informado, caso exista
	 */
	@GetMapping("/{idPessoa}")
	public Optional<Pessoa> consultar(@PathVariable int idPessoa) {
		return pessoaRepository.findById(idPessoa);
	}
	
	/**
	 * Cadastra uma nova pessoa.<br>
	 * Método: POST<br>
	 * Path: /pessoas<br>
	 * Exemplo do JSON:<br>
	 * {<br>
	 *	"nome" : "José Maria da Silva",<br>
	 *	"cpf" : "12345678910",<br>
	 *	"dataNascimento" : "1991-12-31"<br>
	 * }<br>
	 * @param	conta objeto Conta a ser criada
	 * @return	a conta criada
	 */
	@PostMapping
	@ResponseStatus
	public Pessoa adicionar(@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	/**
	 * Exclui uma pessoa.<br>
	 * Método: DELETE<br>
	 * Exemplo do path: /pessoas/1<br>
	 * 
	 * @param	idPessoa a ser excluída
	 * @return	dados da pessoa que foi excluida
	 */
	@DeleteMapping("/{idPessoa}")
	public Pessoa excluir (@PathVariable int idPessoa) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
		pessoaRepository.delete(pessoa.get());
		return pessoa.get();
	}

}
