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

import com.autobots.automanager.entities.Telefone;
import com.autobots.automanager.models.TelefoneAtualizador;
import com.autobots.automanager.models.TelefoneSelecionador;
import com.autobots.automanager.repositories.TelefoneRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {
  @Autowired
  private TelefoneRepositorio repositories;
  @Autowired
  private TelefoneSelecionador selecionador;

  @GetMapping("/telefone/{id}")
  public Telefone obterTelefone(@PathVariable long id) {
    List<Telefone> telefones = repositories.findAll();
    return selecionador.selecionar(telefones, id);
  }

  @GetMapping("/telefones")
  public List<Telefone> obterTelefones() {
    List<Telefone> telefones = repositories.findAll();
    return telefones;
  }

  @PostMapping("/cadastro")
  public void cadastrarTelefone(@RequestBody Telefone telefone) {
    repositories.save(telefone);
  }

  @PutMapping("/atualizar")
  public void atualizarTelefone(@RequestBody Telefone atualizacao) {
    Telefone telefone = repositories.getById(atualizacao.getId());
    TelefoneAtualizador atualizador = new TelefoneAtualizador();
    atualizador.atualizar(telefone, atualizacao);
    repositories.save(telefone);
  }

  @DeleteMapping("/excluir")
  public void excluirTelefone(@RequestBody Telefone exclusao) {
    Telefone telefone = repositories.getById(exclusao.getId());
    repositories.delete(telefone);
  }
}