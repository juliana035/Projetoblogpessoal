package com.JulianaCruz.blogpessoal.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // transforma a classe em uma tabela
@Table(name ="tb_postagens") // renomear a tabela pra eles conseguir se reconhecido
public class Postagem {
    
	@Id // primary key
    @GeneratedValue (strategy = GenerationType.IDENTITY) // auto increment
	private Long id; // criar a variavel 
    
    @NotBlank // obrigatorio preenchimento
    @Size(min = 5, max = 50) // determinamos quanto caracteres terá nosso titulo
    private String titulo;
   
    @NotBlank
    @Size(min = 5, max = 1000)
    private String texto;
   
    @UpdateTimestamp
    private LocalDateTime data;

    @ManyToOne          // criando relações de tabelas, No caso tabela postagem junto com tabela temas
    @JsonIgnoreProperties ("postagem")   // variavel estrangeira
    private Tema tema;               // No caso criamos muitos para um ManyToOne
    
    
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
    
    
    
}
