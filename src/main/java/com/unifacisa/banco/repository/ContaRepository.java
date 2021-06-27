package com.unifacisa.banco.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unifacisa.banco.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{
	
	//Conta findById(int idConta);
	
	@Query("SELECT saldo FROM Conta WHERE idConta = ?1") 
    BigDecimal findSaldoById(Integer idConta);

	@Modifying
	@Transactional
	@Query
	("UPDATE Conta c SET c.flagAtivo =false WHERE c.idConta = ?1")
	int bloqueiaById(@Param("idConta") int idConta);
	
	@Modifying
	@Transactional
	@Query
	("UPDATE Conta c SET c.saldo =?2 WHERE c.idConta = ?1")
	int changeBalanceById(@Param("idConta") int idConta, BigDecimal saldo);

}
