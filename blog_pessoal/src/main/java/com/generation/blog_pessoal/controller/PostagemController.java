package com.generation.blog_pessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blog_pessoal.model.Postagem;
import com.generation.blog_pessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;

	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(postagemRepository.findAll()); // Metodo findAll

		// select * from tb_postagens
	}

	@GetMapping("/{id}") // Variavel de Caminho
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		return postagemRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());

		// select * from tb_postagens where id = id;
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));

		// select * from tb_postagens where titulo like "%titulo%";
	}

	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@Valid @RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));

		// Persistir Status
	}

	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@Valid @RequestBody Postagem postagem) // Equivale ao update do MySQL
	{
		return postagemRepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.ok().body(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.notFound().build());

		// Equivale ao update do MySQL. OBS: se passar o Id, ele entende como
		// atualização, caso contrario entende como um dado novo
	}

	@DeleteMapping("/{id}") // Metodo que não retorna nada automaticamente é void
	public ResponseEntity<?> deletePostagem(@PathVariable long id) {
		return postagemRepository.findById(id).map(resposta -> {
			postagemRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
