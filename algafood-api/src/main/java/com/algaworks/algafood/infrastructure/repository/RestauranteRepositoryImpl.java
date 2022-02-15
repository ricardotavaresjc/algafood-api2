package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	//Implementação consulta dinamica
	@Override
	public List<Restaurante> find(String nome,
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		
		//construtor das clausulas
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		
		//é o mesmo que "from Restaurante"
		//Root é a raiz
		Root<Restaurante> root =  criteria.from(Restaurante.class);
		
		//Criar os predicados(filtros)
		Predicate nomePredicate = builder.like(root.get("nome"), "%" + nome + "%");
		Predicate taxaFreteInicialPredicate = builder
				.greaterThanOrEqualTo(root.get("taxaFreteInicial"), taxaFreteInicial);
		Predicate taxaFreteFinalPredicate = builder
				.greaterThanOrEqualTo(root.get("taxaFreteFinal"), taxaFreteFinal);
		
		//a clausula Where
		criteria.where(nomePredicate,taxaFreteInicialPredicate,taxaFreteFinalPredicate);
		
		//PRecisar criar uma TypedQuery para retornar esse tipo
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	//Implementação rigida
//	@Override
//	public List<Restaurante> find(String nome,
//			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
//		
//		var jqpl = "from Restaurante where nome like :nome "
//				+ "and taxaFrete between :taxaInicial and :taxaFinal";
//		
//		//não permite colocar % direto na string com a instrução jqpl
//		return manager.createQuery(jqpl, Restaurante.class)
//				.setParameter("nome", "%" + nome + "%")
//				.setParameter("taxaInicial", taxaFreteInicial)
//				.setParameter("taxaFinal", taxaFreteFinal)
//				.getResultList();
//	}
	
}
