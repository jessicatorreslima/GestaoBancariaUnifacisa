package com.unifacisa.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unifacisa.banco.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer>{

}
