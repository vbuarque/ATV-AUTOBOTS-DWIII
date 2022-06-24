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

import com.autobots.automanager.entities.Empresa;
import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.entities.UsuarioPerfil;
import com.autobots.automanager.models.AdicionarLinkUsuario;
import com.autobots.automanager.models.EmpresaSelecionador;
import com.autobots.automanager.models.UsuarioSelecionador;
import com.autobots.automanager.repositories.EmpresaRepositorio;
import com.autobots.automanager.repositories.UsuarioRepositorio;
import com.autobots.automanager.services.EmpresaUsuario;

@RestController
public class UsuarioControle {
	@Autowired
	private UsuarioRepositorio repositories;
	@Autowired
	private EmpresaRepositorio repositorioEmpresa;
	@Autowired
	private UsuarioSelecionador selecionador;
	@Autowired
	private EmpresaSelecionador selecionadorEmpresa;
	@Autowired
	private AdicionarLinkUsuario adicionadorLink;
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> obterUsuario(@PathVariable long id) {
		List<Usuario> usuarios = repositories.findAll();
		Usuario usuario = selecionador.selecionar(usuarios, id);
		
		if(usuario == null) {
			ResponseEntity<Usuario> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(usuario);
			ResponseEntity<Usuario> resposta = new ResponseEntity<Usuario>(usuario, HttpStatus.FOUND);
			return resposta;
		}
	}
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> obterUsuarios() {
		List<Usuario> usuarios = repositories.findAll();
		
		if(usuarios.isEmpty()) {
			ResponseEntity<List<Usuario>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(usuarios);
			ResponseEntity<List<Usuario>> resposta = new ResponseEntity<>(usuarios, HttpStatus.FOUND);
			return resposta;
		}
	}
	
	@PostMapping("/usuario/cadastro")
	public ResponseEntity<?> cadastrarUsuario(@RequestBody EmpresaUsuario dadosUsuarioNovo) {
		HttpStatus status = HttpStatus.CONFLICT;
		List<Empresa> empresas = repositorioEmpresa.findAll();
		Empresa selecionada = selecionadorEmpresa.selecionar(empresas, dadosUsuarioNovo.getId());
		
		if (selecionada != null) {
			Usuario dadosUsuarioNovoAdaptado = dadosUsuarioNovo.getUsuarioAdaptar().adaptar();
			selecionada.getUsuarios().add(dadosUsuarioNovoAdaptado);
			repositorioEmpresa.save(selecionada);
			status = HttpStatus.CREATED;
		}
		
		return new ResponseEntity<>(status);
	}
	
	@PostMapping("/usuario/adicionarPerfil")
	public ResponseEntity<?> adicionarPerfil(@RequestBody UsuarioPerfil dadosUsuarioPerfil) {
		HttpStatus status = HttpStatus.CONFLICT;
		List<Usuario> usuarios = repositories.findAll();
		Usuario selecionado = selecionador.selecionar(usuarios, dadosUsuarioPerfil.getId());
		
		if (selecionado != null) {
			selecionado.getPerfis().add(dadosUsuarioPerfil.getPerfil());
			repositories.save(selecionado);
			status = HttpStatus.CREATED;
		}
		
		return new ResponseEntity<>(status);
	}
}