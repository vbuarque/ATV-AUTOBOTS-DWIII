package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entities.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}