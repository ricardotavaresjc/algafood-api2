package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository repository;
	
	@GetMapping
	public List<Cozinha> listar(){
		List<Cozinha> cozinhas = repository.listar();
        
        for (Cozinha cozinha : cozinhas) {
       	 System.out.printf("Id: %d Nome: %s\n",cozinha.getId(),cozinha.getNome());
        }
		return repository.listar();
	}
	
	@GetMapping("/{buscarId}")
	public Cozinha buscar(@PathVariable(name = "buscarId") Long id) {
		return repository.buscar(id);
	}

}
