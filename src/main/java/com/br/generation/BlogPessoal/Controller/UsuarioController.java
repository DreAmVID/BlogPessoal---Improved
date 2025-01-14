package com.br.generation.BlogPessoal.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.server.ResponseStatusException;

import com.br.generation.BlogPessoal.Model.UserLogin;
import com.br.generation.BlogPessoal.Model.Usuario;
import com.br.generation.BlogPessoal.Repository.UsuarioRepository;
import com.br.generation.BlogPessoal.Service.UsuarioService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll(){
		List<Usuario> list = repository.findAll();
		
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(list);
		}
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> findaAllByNome(String nome){
		List<Usuario> list = repository.findAllByNomeContainingIgnoreCase(nome);
		
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(list);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findByID(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@Valid @RequestBody Optional<UserLogin> user){
		return service.Logar(user)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());	
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@Valid @RequestBody Usuario user){
		return service.CadastrarUsuario(user)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElseGet(() -> { 
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!");
				});
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> Put(@Valid @RequestBody Usuario user){
		return repository.findById(user.getId())
				.map(resp -> ResponseEntity.ok(repository.save(user)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity Delete(@PathVariable long id) {
		Optional<Usuario> optional = repository.findById(id);
		if(optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}