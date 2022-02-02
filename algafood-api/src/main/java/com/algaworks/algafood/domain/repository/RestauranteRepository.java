package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {	

	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	//@Query(value = "from Restaurante wherenome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultaPorNome(String nome, @Param("id")Long cozinha);
	
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

}
