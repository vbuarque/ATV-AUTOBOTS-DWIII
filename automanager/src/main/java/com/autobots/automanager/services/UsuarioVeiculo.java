package com.autobots.automanager.services;

import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.entities.Veiculo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioVeiculo {
	private long id;
	private Veiculo veiculo;
}