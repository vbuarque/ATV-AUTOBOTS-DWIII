package com.autobots.automanager.models;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Veiculo;

@Component
public class VeiculoSelecionador {
	public Veiculo selecionar(List<Veiculo> veiculos, long id) {
		Veiculo selecionado = null;
		for (Veiculo veiculo : veiculos) {
			if (veiculo.getId() == id) {
				selecionado = veiculo;
			}
		}
		return selecionado;
	}
}