package com.br.generation.BlogPessoal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.generation.BlogPessoal.Model.Tema;
import com.br.generation.BlogPessoal.Repository.TemaRepository;

@RequestMapping("/tema")
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
	
	/**
	 * Método para postar um tema através da body.
	 * @author @DreAmVID
	 * @since 1.0
	 * @date 04-02-2022
	 *
	 */
	
	@PostMapping
		public ResponseEntity<Tema> PostarUmTema(@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(tema));
	}
	
	/**
	 * Método para editar um tema através da body.
	 * @author @DreAmVID
	 * @since 1.0
	 * @date 04-02-2022
	 *
	 */
	
	@PutMapping
		public ResponseEntity<Tema> EditarUmTema(@RequestBody Tema tema) {
		return ResponseEntity.ok(repository.save(tema));
	}
	
	/**
	 * Método para deletar um tema através do ID.
	 * @author @DreAmVID
	 * @since 1.0
	 * @date 04-02-2022
	 *
	 */
	
	@DeleteMapping("/{id}")
		public void delete (@PathVariable long id) {
		repository.deleteById(id);		
	}
	
}
	
	

