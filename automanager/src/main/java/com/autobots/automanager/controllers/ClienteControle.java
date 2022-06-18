package com.autobots.automanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entities.Cliente;
import com.autobots.automanager.models.ClienteAtualizador;
import com.autobots.automanager.models.ClienteSelecionador;
import com.autobots.automanager.repositories.ClienteRepositorio;

@RestController
@RequestMapping("/cliente")
public class ClienteControle {
	@Autowired
	private ClienteRepositorio repositories;
	@Autowired
	private ClienteSelecionador selecionador;

	@GetMapping("/cliente/{id}")
	public Cliente obterCliente(@PathVariable long id) {
		List<Cliente> clientes = repositories.findAll();
		return selecionador.selecionar(clientes, id);
	}


	@GetMapping("/clientes")
	public List<Cliente> obterClientes() {
		List<Cliente> clientes = repositories.findAll();
		return clientes;
	}

	@PostMapping("/cadastro")
	public void cadastrarCliente(@RequestBody Cliente cliente) {
		repositories.save(cliente);
	}

	@PutMapping("/atualizar")
	public void atualizarCliente(@RequestBody Cliente atualizacao) {
		Cliente cliente = repositories.getById(atualizacao.getId());
		ClienteAtualizador atualizador = new ClienteAtualizador();
		atualizador.atualizar(cliente, atualizacao);
		repositories.save(cliente);
	}

	@DeleteMapping("/excluir")
	public void excluirCliente(@RequestBody Cliente exclusao) {
		Cliente cliente = repositories.getById(exclusao.getId());
		repositories.delete(cliente);
	}
}