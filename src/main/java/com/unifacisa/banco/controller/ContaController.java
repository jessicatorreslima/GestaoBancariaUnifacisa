package com.unifacisa.banco.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

/**
 * @author <a href="malito:jtlimapro@gmail.com">Jéssica Torres de Lima</a>
 *
 */
@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	/**
	 * Lista todas as contas cadastradas.<br>
	 * Método: GET<br>
	 * Path: /contas<br>
	 * @return	uma lista de <code>Conta</code>
	 */
	@GetMapping
	public List<Conta> listar() {
		return contaRepository.findAll();
	}
	
	/**
	 * Busca uma conta pelo idConta.<br>
	 * Método: GET<br>
	 * Exemplo do path: /contas/1<br>
	 * 
	 * @param	idConta da conta a ser localizada<br>
	 * @return	a conta que possui o idConta informado, caso exista.
	 */
	@GetMapping("/{idConta}")
	public Optional<Conta> consultar(@PathVariable int idConta) {
		return contaRepository.findById(idConta);
	}
	
	/**
	 * Cria uma nova conta.<br>
	 * Método: POST<br>
	 * Path: /contas<br>
	 * Exemplo do JSON:<br>
	 * {<br>
	 * 	"idPessoa" : 1,<br>
	 * 	"saldo" : 1000.00,<br>
	 * 	"limiteSaqueDiario" : 500.00,<br>
	 * 	"flagAtivo" : 1,<br>
	 * 	"tipoConta" : 2<br>
	 * }<br>
	 * 
	 * @param	conta em formato JSON a ser criada
	 * @return	a conta criada
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Conta adicionar(@RequestBody Conta conta) {
		return contaRepository.save(conta);
	}
	
	@PutMapping("/{idConta}")
	public Conta atualizar(@RequestBody Conta conta) {
		return contaRepository.save(conta);
	}
	
	/**
	 * Exibe o saldo de uma conta.<br>
	 * Método: GET<br>
	 * Exemplo do path: /contas/saldo/2<br>
	 * 
	 * @param	idConta da conta que quer consultar o saldo
	 * @return	o saldo da conta que possui o idConta informado, caso exista
	 */
	@GetMapping("/saldo/{idConta}")
	public BigDecimal consultarSaldo(@PathVariable int idConta) {
		return contaRepository.findSaldoById(idConta);
	}
	
	/**
	 * Bloqueia uma conta.<br>
	 * Método: PUT<br>
	 * Exemplo do path: /contas/bloqueio/3<br>
	 * 
	 * @param	idConta da conta a ser bloqueada
	 * @return	1 se a conta foi bloqueada<br>
	 * 			0 caso a conta não exista
	 */
	@PutMapping("/bloqueio/{idConta}")
	public int bloquear(@PathVariable int idConta) {
		return contaRepository.bloqueiaById(idConta);
	}
	
	/**
	 * Deposita um valor numa conta.<br>
	 * Será registrado uma nova <code>Transacao</code><br>
	 * Método: PUT<br>
	 * Exemplo do path: /contas/deposito?idConta=4&valor=100.00<br>
	 * 
	 * @param	idConta da conta a receber o depósito
	 * @param	valor a ser depositado
	 * @return	1 se o depósito foi realizado com sucesso
	 */
	@PutMapping("/deposito")
	public int depositar(@RequestParam int idConta, @RequestParam BigDecimal valor) {
		Conta conta = contaRepository.findById(idConta).get();
		BigDecimal novoSaldo = conta.getSaldo().add(valor);
		Transacao transacao = new Transacao(conta,valor);
		transacaoRepository.save(transacao);
		return contaRepository.changeBalanceById(idConta,novoSaldo);
	}	
	
	/**
	 * Saca um valor numa conta.<br>
	 * Será registrado uma nova <code>Transacao</code> com valor negativo<br>
	 * Método: PUT<br>
	 * Exemplo do path: /contas/saque?idConta=5&valor=200.00<br>
	 * 
	 * @param	idConta da conta de onde será sacado
	 * @param	valor a ser sacado
	 * @return	1 se o saque foi realizado com sucesso<br>
	 * 			0 se não há saldo suficiente
	 */
	@PutMapping("/saque")
	public int sacar(@RequestParam int idConta, @RequestParam BigDecimal valor) {
		Conta conta = contaRepository.findById(idConta).get();
		BigDecimal novoSaldo = conta.getSaldo();
		if (conta.getSaldo().subtract(valor).compareTo(new BigDecimal("0")) >= 0)
		{
			novoSaldo = conta.getSaldo().subtract(valor);
			Transacao transacao = new Transacao(conta,valor.negate());
			transacaoRepository.save(transacao);
			return contaRepository.changeBalanceById(idConta,novoSaldo);
		} 
		return 0;
	}
	
	/**
	 * Consulta o extrato de uma conta.<br>
	 * Método: GET<br>
	 * Exemplo do path: /contas/extrato/6<br>
	 * 
	 * @param	idConta da conta a ser buscado o extrato
	 * @return	uma lista das transações realizadas na conta
	 */
	@GetMapping("/extrato/{idConta}")
	public List<Transacao> consultarExtrato(@PathVariable int idConta) {
		return transacaoRepository.findByConta(contaRepository.findById(idConta));
	}
	
	/**
	 * Consulta o extrato de uma conta por período.<br>
	 * Método: GET<br>
	 * Exemplo do path: /contas/extrato/periodo?idConta=7&dataInicial=2021-05-01T00:00:00&dataFinal=2021-05-31T00:00:00<br>
	 * 
	 * @param	idConta da conta a ser buscado o extrato
	 * @param	dataInicial quando começa o período desejado
	 * @param	dataFinal quando termina o período desejado
	 * @return	uma lista das transações realizadas na conta dentro do período informado
	 */
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
