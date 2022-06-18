package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entities.Telefone;

public interface TelefoneRepositorio extends JpaRepository<Telefone, Long> {
}