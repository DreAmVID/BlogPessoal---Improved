package com.br.generation.BlogPessoal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.generation.BlogPessoal.Model.Tema;

	@Repository
	public interface TemaRepository extends JpaRepository<Tema, Long> {
	
	// Métodos
	
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
	
}
