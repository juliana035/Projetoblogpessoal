package com.JulianaCruz.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // criação de tabela 
@Table(name = "tb_temas") // criação de tabela
public class Tema {
	
	@Id // Primary Key
	@GeneratedValue (strategy = GenerationType.IDENTITY) //Auto incremente
	private Long id;
	
	@NotBlank
	private String descricao;
    
	@OneToMany (mappedBy = "tema", cascade = CascadeType.REMOVE) //sempre criando de uma pra muitos                                                  
	@JsonIgnoreProperties("tema")                                 // tirar o ALL e por o REMOVE
	private List<Postagem>postagem;         
	
	
	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
