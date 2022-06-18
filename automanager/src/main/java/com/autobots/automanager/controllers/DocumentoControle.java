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

import com.autobots.automanager.entities.Documento;
import com.autobots.automanager.models.DocumentoAtualizador;
import com.autobots.automanager.models.DocumentoSelecionador;
import com.autobots.automanager.repositories.DocumentoRepositorio;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {
	@Autowired
	private DocumentoRepositorio repositories;
	@Autowired
	private DocumentoSelecionador selecionador;

	@GetMapping("/documento/{id}")
	public Documento obterDocumento(@PathVariable long id) {
		List<Documento> documentos = repositories.findAll();
		return selecionador.selecionar(documentos, id);
	}

	@GetMapping("/documentos")
	public List<Documento> obterDocumentos() {
		List<Documento> documentos = repositories.findAll();
		return documentos;
	}

	@PostMapping("/cadastro")
	public void cadastrarDocumento(@RequestBody Documento documento) {
		repositories.save(documento);
	}

	@PutMapping("/atualizar")
	public void atualizarDocumento(@RequestBody Documento atualizacao) {
		Documento documento = repositories.getById(atualizacao.getId());
		DocumentoAtualizador atualizador = new DocumentoAtualizador();
		atualizador.atualizar(documento, atualizacao);
		repositories.save(documento);
	}

	@DeleteMapping("/excluir")
	public void excluirDocumento(@RequestBody Documento exclusao) {
		Documento documento = repositories.getById(exclusao.getId());
		repositories.delete(documento);
	}
}