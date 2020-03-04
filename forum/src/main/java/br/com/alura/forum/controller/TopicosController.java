package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicosController {

	@Autowired
	TopicoRepository topicoRepository;

	@RequestMapping("/topicos")
	public List<TopicoDTO> lista(){
		return TopicoDTO.converter(topicoRepository.findAll());
	}
}