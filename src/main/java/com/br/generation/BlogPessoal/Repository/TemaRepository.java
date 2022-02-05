package com.br.generation.BlogPessoal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.generation.BlogPessoal.Model.Tema;

/**
 * Classe repository utilizada para recuperar métodos do JpaRepository e definir método
 * findAllByDescricaoContainingIgnoreCase
 * @author @DreAmVID
 * @since 1.0
 * @date 04-02-2022
 *
 */

	@Repository
	public interface TemaRepository extends JpaRepository<Tema, Long> {
	
	// Métodos
	
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
	
}
