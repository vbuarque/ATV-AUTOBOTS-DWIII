package com.autobots.automanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entities.Mercadoria;
import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.entities.Veiculo;
import com.autobots.automanager.models.AdicionadorLinkMercadoria;
import com.autobots.automanager.models.UsuarioSelecionador;
import com.autobots.automanager.models.MercadoriaSelecionador;
import com.autobots.automanager.repositories.MercadoriaRepositorio;
import com.autobots.automanager.repositories.UsuarioRepositorio;
import com.autobots.automanager.services.UsuarioMercadoria;
import com.autobots.automanager.services.UsuarioVeiculo;

@RestController
public class MercadoriaControle {
	@Autowired
	private MercadoriaRepositorio repositories;
	@Autowired
	private UsuarioRepositorio repositorioUsuario;
	@Autowired
	private AdicionadorLinkMercadoria adicionadorLink;
	@Autowired
	private UsuarioSelecionador selecionadorUsuario;
	@Autowired
	private MercadoriaSelecionador selecionador;
	
	@GetMapping("/mercadoria/{id}")
	public ResponseEntity<Mercadoria> obterMercadoria(@PathVariable long id) {
		List<Mercadoria> mercadorias = repositories.findAll();
		Mercadoria mercadoria = selecionador.selecionar(mercadorias, id);
		
		if(mercadoria == null) {
			ResponseEntity<Mercadoria> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(mercadoria);
			ResponseEntity<Mercadoria> resposta = new ResponseEntity<Mercadoria>(mercadoria, HttpStatus.FOUND);
			return resposta;
		}
	}
	
	@GetMapping("/mercadorias")
	public ResponseEntity<List<Mercadoria>> obterMercadorias() {
		List<Mercadoria> mercadorias = repositories.findAll();
		
		if(mercadorias.isEmpty()) {
			ResponseEntity<List<Mercadoria>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(mercadorias);
			ResponseEntity<List<Mercadoria>> resposta = new ResponseEntity<>(mercadorias, HttpStatus.FOUND);
			return resposta;
		}
	}
	
	@PostMapping("/mercadoria/cadastro")
	public ResponseEntity<?> cadastrarVeiculo(@RequestBody UsuarioMercadoria informacoesMercadoriaNova) {
		HttpStatus status = HttpStatus.CONFLICT;
		
		List<Usuario> usuarios = repositorioUsuario.findAll();
		Usuario selecionado = selecionadorUsuario.selecionar(usuarios, informacoesMercadoriaNova.getId());
		
		if (selecionado != null) {
			selecionado.getMercadorias().add(informacoesMercadoriaNova.getMercadoria());
			repositorioUsuario.save(selecionado);
			status = HttpStatus.CREATED;
		}
		
		return new ResponseEntity<>(status);
	}
}