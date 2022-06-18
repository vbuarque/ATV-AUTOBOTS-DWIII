package com.autobots.automanager.models;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Telefone;

@Component
public class TelefoneSelecionador {
  public Telefone selecionar(List<Telefone> telefones, long id) {
    Telefone selecionado = null;
    for (Telefone telefone : telefones) {
      if (telefone.getId() == id) {
        selecionado = telefone;
      }
    }
    return selecionado;
  }
}