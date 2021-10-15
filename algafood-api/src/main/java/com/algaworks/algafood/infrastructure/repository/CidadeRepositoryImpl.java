package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {
	
	@Autowired
	private EntityManager em;

	@Override
	public List<Cidade> listar() {		
		return em.createQuery("from Cidade", Cidade.class).getResultList();
	}

	@Override
	public Cidade buscar(Long id) {
		return em.find(Cidade.class, id);
	}

	@Transactional
	@Override
	public Cidade adcionar(Cidade cidade) {
		return em.merge(cidade);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		 Cidade cidade = buscar(id);
		 
		 if (cidade == null) {
			 throw new EmptyResultDataAccessException(1);
		 }
		 
		 em.remove(cidade);
		
	}

}
