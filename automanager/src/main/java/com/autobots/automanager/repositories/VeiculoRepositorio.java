package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entities.Veiculo;

public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {

}