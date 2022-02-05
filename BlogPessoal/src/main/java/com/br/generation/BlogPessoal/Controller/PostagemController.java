package com.br.generation.BlogPessoal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}

}
