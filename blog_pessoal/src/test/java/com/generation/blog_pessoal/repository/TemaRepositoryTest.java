package com.generation.blog_pessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blog_pessoal.model.Tema;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TemaRepositoryTest {
	
	@Autowired
	private TemaRepository temaRepository;
	
	@BeforeAll
	void start() {
		temaRepository.save(new Tema(0L, "Esportes"));
		temaRepository.save(new Tema(0L, "Games"));
		temaRepository.save(new Tema(0L, "Quadrinhos da Marvel"));
		temaRepository.save(new Tema(0L, "Quadrinhos da DC"));
		temaRepository.save(new Tema(0L, "Livros"));
	}
	
	@Test
	@DisplayName("Retorna 1 Tema")
	public void deveRetornarUmTema() {
		Optional <Tema> tema = temaRepository.findByDescricao("Games");
		assertTrue(tema.get().getDescricao().equals("Games"));
	}
	
	@Test
	@DisplayName("Retorna 2 Temas")
	public void deveRetornarDoisTemas() {
		List<Tema> listaDeTemas = temaRepository.findAllByDescricaoContainingIgnoreCase("Quadrinhos");
		assertEquals(2, listaDeTemas.size());		
		assertTrue(listaDeTemas.get(0).getDescricao().equals("Quadrinhos da Marvel"));
		assertTrue(listaDeTemas.get(1).getDescricao().equals("Quadrinhos da DC"));		
	}

}
