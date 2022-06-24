package com.autobots.automanager.models;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Servico;

@Component
public class ServicoSelecionador {
	public Servico selecionar(List<Servico> servicos, long id) {
		Servico selecionado = null;
		for (Servico servico : servicos) {
			if (servico.getId() == id) {
				selecionado = servico;
			}
		}
		return selecionado;
	}
}