package com.autobots.automanager.services;

import com.autobots.automanager.entities.Mercadoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioMercadoria {
	private long id;
	private Mercadoria mercadoria;
}