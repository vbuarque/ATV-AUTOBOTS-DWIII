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

import com.autobots.automanager.entities.Endereco;
import com.autobots.automanager.models.EnderecoAtualizador;
import com.autobots.automanager.models.EnderecoSelecionador;
import com.autobots.automanager.repositories.EnderecoRepositorio;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {
  @Autowired
  private EnderecoRepositorio repositories;
  @Autowired
  private EnderecoSelecionador selecionador;

  @GetMapping("/endereco/{id}")
  public Endereco obterEndereco(@PathVariable long id) {
    List<Endereco> enderecos = repositories.findAll();
    return selecionador.selecionar(enderecos, id);
  }

  @GetMapping("/enderecos")
  public List<Endereco> obterEnderecos() {
    List<Endereco> enderecos = repositories.findAll();
    return enderecos;
  }

  @PostMapping("/cadastro")
  public void cadastrarEndereco(@RequestBody Endereco endereco) {
    repositories.save(endereco);
  }

  @PutMapping("/atualizar")
  public void atualizarEndereco(@RequestBody Endereco atualizacao) {
    Endereco endereco = repositories.getById(atualizacao.getId());
    EnderecoAtualizador atualizador = new EnderecoAtualizador();
    atualizador.atualizar(endereco, atualizacao);
    repositories.save(endereco);
  }

  @DeleteMapping("/excluir")
  public void excluirEndereco(@RequestBody Endereco exclusao) {
    Endereco endereco = repositories.getById(exclusao.getId());
    repositories.delete(endereco);
  }
}