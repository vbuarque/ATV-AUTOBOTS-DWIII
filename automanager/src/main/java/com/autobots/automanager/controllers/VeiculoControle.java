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

import com.autobots.automanager.models.AdicionadorLinkVeiculo;
import com.autobots.automanager.models.UsuarioSelecionador;
import com.autobots.automanager.models.VeiculoSelecionador;
import com.autobots.automanager.entities.Empresa;
import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.entities.Veiculo;
import com.autobots.automanager.repositories.UsuarioRepositorio;
import com.autobots.automanager.repositories.VeiculoRepositorio;
import com.autobots.automanager.services.UsuarioVeiculo;


@RestController
public class VeiculoControle {
	@Autowired
	private VeiculoRepositorio repositories;
	@Autowired
	private UsuarioRepositorio repositorioUsuario;
	@Autowired
	private AdicionadorLinkVeiculo adicionadorLink;
	@Autowired
	private VeiculoSelecionador selecionador;
	@Autowired
	private UsuarioSelecionador selecionadorUsuario;
	
	@GetMapping("/veiculo/{id}")
	public ResponseEntity<Veiculo> obterVeiculo(@PathVariable long id) {
		List<Veiculo> veiculos = repositories.findAll();
		Veiculo veiculo = selecionador.selecionar(veiculos, id);
		
		if(veiculo == null) {
			ResponseEntity<Veiculo> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(veiculo);
			ResponseEntity<Veiculo> resposta = new ResponseEntity<Veiculo>(veiculo, HttpStatus.FOUND);
			return resposta;
		}
	}
	
	@GetMapping("/veiculos")
	public ResponseEntity<List<Veiculo>> obterVeiculos() {
		List<Veiculo> veiculos = repositories.findAll();
		
		if(veiculos.isEmpty()) {
			ResponseEntity<List<Veiculo>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(veiculos);
			ResponseEntity<List<Veiculo>> resposta = new ResponseEntity<>(veiculos, HttpStatus.FOUND);
			return resposta;
		}
	}
	
	@PostMapping("/veiculo/cadastro")
	public ResponseEntity<?> cadastrarVeiculo(@RequestBody UsuarioVeiculo informacoesVeiculoNovo) {
		HttpStatus status = HttpStatus.CONFLICT;
		
		List<Usuario> usuarios = repositorioUsuario.findAll();
		Usuario selecionado = selecionadorUsuario.selecionar(usuarios, informacoesVeiculoNovo.getId());
		
		if (selecionado != null) {
			selecionado.getVeiculos().add(informacoesVeiculoNovo.getVeiculo());
			repositorioUsuario.save(selecionado);
			status = HttpStatus.CREATED;
		}
		
		return new ResponseEntity<>(status);
	}
}