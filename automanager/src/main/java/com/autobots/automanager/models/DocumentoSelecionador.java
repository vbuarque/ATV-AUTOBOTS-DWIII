package com.autobots.automanager.models;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Documento;

@Component
public class DocumentoSelecionador {
  public Documento selecionar(List<Documento> documentos, long id) {
    Documento selecionado = null;
    for (Documento documento : documentos) {
      if (documento.getId() == id) {
        selecionado = documento;
      }
    }
    return selecionado;
  }
}