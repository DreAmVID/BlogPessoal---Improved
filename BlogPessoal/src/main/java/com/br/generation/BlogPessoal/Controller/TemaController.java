package com.br.generation.BlogPessoal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.generation.BlogPessoal.Model.Tema;
import com.br.generation.BlogPessoal.Repository.TemaRepository;

@RequestMapping("/temas")
@CrossOrigin (origins = "*", allowedHeaders = "*")
@RestController
public class TemaController {

	@Autowired
	private TemaRepository repository;
	
	/**
	 * Método para retornar na body todos os temas cadastrados.
	 * @author @DreAmVID
	 * @since 1.0
	 * @date 04-02-2022
	 *
	 */
	
	@GetMapping
		public ResponseEntity<List<Tema>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
		
	}
	
	/**
	 * Método para retornar na body um tema de acordo com o ID.
	 * @author @DreAmVID
	 * @since 1.0
	 * @date 04-02-2022
	 *
	 */
	
	@GetMapping ("/id/{id}")
		public ResponseEntity<Tema> GetById (@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * Método para retornar na body um lista de temas de acordo com a descrição.
	 * @author @DreAmVID
	 * @since 1.0
	 * @date 04-02-2022
	 *
	 */
	
	@GetMapping("/descricao/{descricao}")
		public ResponseEntity<List<Tema>> GetByDescricao (@PathVariable String descricao) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));		
	}
	
}
