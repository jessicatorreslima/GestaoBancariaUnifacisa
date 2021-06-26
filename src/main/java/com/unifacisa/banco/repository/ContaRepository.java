package com.unifacisa.banco.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unifacisa.banco.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{
	Conta findById(int idConta);
	
	@Query("SELECT saldo FROM Conta WHERE idConta = ?1") 
    BigDecimal findSaldoById(Integer idConta);

}
