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

import com.br.generation.BlogPessoal.Model.Postagem;
import com.br.generation.BlogPessoal.Repository.PostagemRepository;

@RequestMapping("/postagens")
@CrossOrigin ("*")
@RestController
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	/**
	 * Método para retornar na body todos as postagens cadastradas 
	 * @author @DreAmVID
	 * @since 1.0
	 * @date 04-02-2022
	 *
	 */
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll()	{
		return ResponseEntity.ok(repository.findAll());	
	}
	
	/**
	 * Método para retornar na body as postagens através de um filtro de ID
	 * utilizado condição λ para retornar em condições de requisições nulas ou incorretas
	 * e em caso de solicitações que atendem ao método.
	 * @author @DreAmVID
	 * @since 1.0
	 * @date 04-02-2022
	 *
	 */
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id) {
			return repository.findById(id)
					.map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * Método para retornar na body as postagens através de um filtro de titulo
	 * note que neste caso é possível que exista mais de uma postagem com o mesmo titulo
	 * logo a resposta deve ser retornada atráves de uma lista
	 * @author @DreAmVID
	 * @since 1.0
	 * @date 04-02-2022
	 *
	 */
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	/**
	 * Método para enviar através da body uma postagem.
	 * @author @DreAmVID
	 * @since 1.0
	 * @date 04-02-2022
	 *
	 */
	
	@PostMapping
	public ResponseEntity<Postagem> CriarUmPost(@RequestBody Postagem conteudo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(conteudo));	
	}

	/**
	 * Método para editar através da body uma postagem.
	 * @author @DreAmVID
	 * @since 1.0
	 * @date 04-02-2022
	 *
	 */
	
	@PutMapping	
	public ResponseEntity<Postagem> EditarUmPost(@RequestBody Postagem conteudo) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(conteudo));	
	}
	
	/**
	 * Método para deletar uma postagem através do numero de ID.
	 * @author @DreAmVID
	 * @since 1.0
	 * @date 04-02-2022
	 *
	 */
	
	@DeleteMapping("/{id}")
	public void DeletarUmPost(@PathVariable long id) {
		repository.deleteById(id);
		
	}
		
}
