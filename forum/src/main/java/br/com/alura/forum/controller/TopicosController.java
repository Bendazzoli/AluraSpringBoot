package br.com.alura.forum.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.dto.DetalhesTopicoDTO;
import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value="/topicos")
public class TopicosController {

	@Autowired
	TopicoRepository topicoRepository;

	@Autowired
	CursoRepository cursoRepository;

	@GetMapping
	public Page<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso,
								 @RequestParam int pagina,
								 @RequestParam int quantidade,
								 @RequestParam String ordenacao){

		Pageable paginacao = PageRequest.of(pagina - 1, quantidade, Sort.Direction.ASC, ordenacao);

		if(nomeCurso == null) {
			return TopicoDTO.converter(topicoRepository.findAll(paginacao));
		}else{
			return TopicoDTO.converter(topicoRepository.findByCurso_Nome(nomeCurso, paginacao));
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesTopicoDTO> detalharUmTopico(@PathVariable Long id){
		Optional<Topico> optionalTopico = topicoRepository.findById(id);
		if(optionalTopico.isPresent()) {
			return ResponseEntity.ok(new DetalhesTopicoDTO(optionalTopico.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
		Optional<Topico> optionalTopico = topicoRepository.findById(id);
		if(optionalTopico.isPresent()){
			Topico topico = form.atualizar(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDTO(topico));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deleta(@PathVariable Long id){
		Optional<Topico> optionalTopico = topicoRepository.findById(id);
		if (optionalTopico.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}