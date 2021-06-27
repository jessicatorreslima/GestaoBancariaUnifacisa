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
	 * Lista todas as pessoas cadastradas.
	 * Método: GET
	 * Path: /pessoas
	 * @return	uma lista de <code>Pessoa</code>
	 */
	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	/**
	 * Busca uma pessoa pelo idPessoa.
	 * Método: GET
	 * Exemplo do path: /pessoas/1
	 * 
	 * @param	idPessoa
	 * @return	a pessoa que possui o idPessoa informado, caso exista
	 */
	@GetMapping("/{idPessoa}")
	public Optional<Pessoa> consultar(@PathVariable int idPessoa) {
		return pessoaRepository.findById(idPessoa);
	}
	
	/**
	 * Cadastra uma nova pessoa.
	 * Método: POST
	 * Path: /pessoas
	 * Exemplo do JSON:
	 * {
	 *	"nome" : "José Maria da Silva",
	 *	"cpf" : "12345678910",
	 *	"dataNascimento" : "1991-12-31"
	 * }
	 * @param	conta
	 * @return	a conta criada
	 */
	@PostMapping
	@ResponseStatus
	public Pessoa adicionar(@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	/**
	 * Exclui uma pessoa.
	 * Método: DELETE
	 * Exemplo do path: /pessoas/1
	 * 
	 * @param	idPessoa
	 * @return	a pessoa que foi excluida
	 */
	@DeleteMapping("/{idPessoa}")
	public Pessoa excluir (@PathVariable int idPessoa) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
		pessoaRepository.delete(pessoa.get());
		return pessoa.get();
	}

}
