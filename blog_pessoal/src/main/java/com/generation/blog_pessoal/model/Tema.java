package com.generation.blog_pessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_tema")
public class Tema {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O ATRIBUTO DESCRIÇÃO É OBRIGATÓRIO!")
	@Size(min = 5, max = 50, message = "O ATRIBUTO DESCRIÇÃO DEVE TER NO MINIMO 5 E NO MAXIMO 50 CARACTERES!")
	private String descricao;
	
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem; // Equivale a tb_tema.id
	
	public Tema(long id, String descricao) {		
		this.id = id;
		this.descricao = descricao;
	}
	
	public Tema() {	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
		
}
