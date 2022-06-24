package com.autobots.automanager.models;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.VeiculoControle;
import com.autobots.automanager.entities.Veiculo;

@Component
public class AdicionadorLinkVeiculo implements AdicionadorLink<Veiculo> {
	@Override
	public void adicionarLink(List<Veiculo> lista) {
		for (Veiculo veiculo : lista) {
			long id = veiculo.getId();
			Link linkProprio = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VeiculoControle.class)
							.obterVeiculo(id))
					.withSelfRel();
			veiculo.add(linkProprio);
		}
	}

	@Override
	public void adicionarLink(Veiculo objeto) {
		Link linkProprio = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VeiculoControle.class)
						.obterVeiculos())
				.withRel("veiculo");
		objeto.add(linkProprio);
	}
}