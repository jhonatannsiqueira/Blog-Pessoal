package com.generation.blog_pessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blog_pessoal.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
	
	public Optional<Usuario> findByUsuario(String usuario);
	// Optional, pois essa pesquisa pode retornar um Nulo.
	/* ANOTAÇÃO DO INSTRUTOR	
	 * Método que busca um Usuário pelo seu Usuario (E-mail). 
	 * select * from tb_usuarios where usuario = "usuario procurado"
	 */
	
	public List <Usuario> findAllByNomeContainingIgnoreCase(String nome);
}
