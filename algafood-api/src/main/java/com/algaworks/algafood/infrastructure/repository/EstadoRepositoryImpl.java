package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Estado> listar() {		
		return em.createQuery("from Estado", Estado.class).getResultList();
	}

	@Override
	public Estado buscar(Long id) {
		return em.find(Estado.class, id);
	}

	@Transactional
	@Override
	public Estado adcionar(Estado estado) {		
		return em.merge(estado);
	}

	@Override
	public void remover(Estado estado) {
		buscar(estado.getId());
		em.remove(estado);
		
	}
	
	

}
