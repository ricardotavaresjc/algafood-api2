package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Cozinha> listar() {		
		return em.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
	
	@Override
	public List<Cozinha> consultarPorNome(String nomeCozinha) {		
		return em.createQuery("from Cozinha where nome = :nome",Cozinha.class)
				.setParameter("nome", nomeCozinha)
				.getResultList();
	}
	

	@Override
	public Cozinha buscar(Long id) {
		return em.find(Cozinha.class, id);
	}

	@Transactional
	@Override
	public Cozinha adcionar(Cozinha cozinha) {		
		return em.merge(cozinha);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Cozinha cozinha = buscar(id);
		
		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}	
		
		em.remove(cozinha);		
	}



	
	

}
