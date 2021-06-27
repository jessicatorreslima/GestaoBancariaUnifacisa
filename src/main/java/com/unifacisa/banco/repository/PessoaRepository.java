package com.unifacisa.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unifacisa.banco.model.Pessoa;
/**
 * @author <a href="malito:jtlimapro@gmail.com">Jéssica Torres de Lima</a>
 *
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}