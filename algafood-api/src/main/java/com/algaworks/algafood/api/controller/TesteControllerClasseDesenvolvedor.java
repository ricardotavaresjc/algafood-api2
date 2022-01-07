package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/teste")
public class TesteControllerClasseDesenvolvedor {
	
	@Autowired
	private CozinhaRepository repository;
	
//	@GetMapping("/cozinhas/por-nome")
//	public List<Cozinha> consultarPorNome(@RequestParam ("nome") String nome){
//		return repository.consultarPorNome(nome);
//	}

}
