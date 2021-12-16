package com.generation.blog_pessoal.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // Transforma a Classe em Tabela - create table
@Table(name = "tb_postagens") // tb_postagens
public class Postagem {

	@Id // Informa que esse valor é a Chave Primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
	private Long id; // Classe que irá gerar Objetos -Chave Primaria

	@NotBlank(message = "O ATRIBUTO TITULO É OBRIGATÓRIO!") // Equivale a NotNull e não espaço em branco
	@Size(min = 5, max = 100, message = "O ATRIBUTO TITULO DEVE TER NO MINIMO 5 E NO MAXIMO 100 CARACTERES!")
	private String titulo; // Titulo da Postagem

	@NotNull(message = "O ATRIBUTO TEXTO É OBRIGATÓRIO!") // Equivale a NotNull e sem espaçamento
	@Size(min = 10, max = 1000, message = "O ATRIBUTO TEXTO DEVE TER NO MINIMO 10 E NO MAXIMO 1000 CARACTERES!")
	private String texto; // Conteudo da Postagem

	@UpdateTimestamp
	private LocalDate data; // Classe para Data
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema; // Equivale a tema_id
	
	@ManyToOne
	@JsonIgnoreProperties("postagem") // Equivale a usuario_id
	private Usuario usuario;

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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
