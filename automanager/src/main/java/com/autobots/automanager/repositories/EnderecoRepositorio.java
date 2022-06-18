package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entities.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {
}