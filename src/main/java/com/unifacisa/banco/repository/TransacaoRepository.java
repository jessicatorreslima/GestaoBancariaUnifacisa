package com.unifacisa.banco.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unifacisa.banco.model.Conta;
import com.unifacisa.banco.model.Transacao;
/**
 * @author <a href="malito:jtlimapro@gmail.com">Jéssica Torres de Lima</a>
 *
 */
public interface TransacaoRepository extends JpaRepository<Transacao, Integer>{

	@Query("SELECT valor FROM Transacao WHERE idConta = ?1") 
    BigDecimal findValorById(Integer idConta);

	List<Transacao> findByConta(Optional<Conta> conta);
	
	List<Transacao> findByContaAndDataTransacaoGreaterThanAndDataTransacaoLessThan(Optional<Conta> conta, LocalDateTime dataInicial, LocalDateTime dataFinal);
	
}