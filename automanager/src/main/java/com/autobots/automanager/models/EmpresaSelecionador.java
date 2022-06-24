package com.autobots.automanager.models;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Empresa;

@Component
public class EmpresaSelecionador {
	public Empresa selecionar(List<Empresa> empresas, long id) {
		Empresa selecionado = null;
		for (Empresa empresa : empresas) {
			if (empresa.getId() == id) {
				selecionado = empresa;
			}
		}
		return selecionado;
	}
}