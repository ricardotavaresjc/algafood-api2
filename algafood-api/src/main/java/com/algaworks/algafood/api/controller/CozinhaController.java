package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository repository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@GetMapping
	public List<Cozinha> listar(){
		List<Cozinha> cozinhas = repository.listar();
        
        for (Cozinha cozinha : cozinhas) {
       	 System.out.printf("Id: %d Nome: %s\n",cozinha.getId(),cozinha.getNome());
        }
		return repository.listar();
	}
	
	@GetMapping("/{buscarId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable(name = "buscarId") Long id) {
		Cozinha cozinha = repository.buscar(id);
		
		if(cozinha != null) {
			return ResponseEntity.ok(cozinha);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cozinha adcionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}
	
	@PutMapping("/{buscarId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long buscarId, @RequestBody Cozinha cozinha){
		Cozinha cozinhaAtual = repository.buscar(buscarId);
		
		//Caso exista ele atualiza, se não ele retorna um status 400 not found
		if (cozinhaAtual !=null){
			/*usado para copiar as propriedades de um objeto para o outro, nesse caso ele so esta ignorando "id" 
			para nao copiar um id nullo. Com se fosse cozinhaAtual.setNome(cozinha.getNome)*/			
			BeanUtils.copyProperties(cozinha, cozinhaAtual,"id");
			cadastroCozinha.salvar(cozinhaAtual);
			
			return ResponseEntity.ok(cozinhaAtual);
			
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId){
		
		try {
			cadastroCozinha.excluir(cozinhaId);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		}
		
		
	}

}
