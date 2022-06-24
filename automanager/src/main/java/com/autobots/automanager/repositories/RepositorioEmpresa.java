package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.autobots.automanager.entities.Empresa;

public interface RepositorioEmpresa extends JpaRepository<Empresa, Long> {

}