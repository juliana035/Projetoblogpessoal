package com.JulianaCruz.blogpessoal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JulianaCruz.blogpessoal.model.Tema;

public interface TemaRepository extends JpaRepository<Tema,Long> {
	public List<Tema>findAllByDescricaoContainingIgnoreCase(String descricao);
	
}
