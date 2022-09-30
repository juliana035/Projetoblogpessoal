package com.JulianaCruz.blogpessoal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JulianaCruz.blogpessoal.Repository.PostagemRepository;
import com.JulianaCruz.blogpessoal.model.Postagem;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins ="*",allowedHeaders ="*")

public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;

	public ResponseEntity<List<Postagem>> getAll(){
	return ResponseEntity.ok(postagemRepository.findAll());
	}
      
}
