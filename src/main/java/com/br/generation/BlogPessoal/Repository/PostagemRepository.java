package com.br.generation.BlogPessoal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.generation.BlogPessoal.Model.Postagem;

/**
 * Classe repository utilizada para recuperar métodos do JpaRepository e definir método
 * FindAllByTitulo
 * @author @DreAmVID
 * @since 1.0
 * @date 04-02-2022
 *
 */

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);
	
}
