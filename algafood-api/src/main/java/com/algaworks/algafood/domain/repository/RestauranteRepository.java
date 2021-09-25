package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cozinha;


public interface RestauranteRepository {
	
	public List<Cozinha> listar();
	
	public Cozinha buscar(Long id);
	
	public Cozinha adcionar(Cozinha cozinha);
	
	public void remover(Cozinha cozinha);

}
