package com.algaworks.algafood.domain.service;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;	
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
//	public Restaurante atualizar(Long id, Restaurante restaurante) {
//		Restaurante restauranteAtualizado = restauranteRepository.buscar(id);		
//		
//		if(restauranteAtualizado == null) {
//			throw new EntidadeNaoEncontradaException(
//					String.format("Não existe restaurante com o codigo %d", id));
//		}
//		
//		BeanUtils.copyProperties(restaurante, restauranteAtualizado,"id");
//		return salvar(restauranteAtualizado);
//	}
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaID = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaID);
		
		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com codigo %d",cozinhaID ));
		}
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.adcionar(restaurante);
	}
	

}
