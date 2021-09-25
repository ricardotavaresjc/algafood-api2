package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Cozinha> listar() {		
		return em.createQuery("from Cozinha", Cozinha.class).getResultList();
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

	@Override
	public void remover(Cozinha cozinha) {
		buscar(cozinha.getId());
		em.remove(cozinha);
		
	}
	
	

}
