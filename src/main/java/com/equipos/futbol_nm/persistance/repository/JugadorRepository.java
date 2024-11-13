package com.equipos.futbol_nm.persistance.repository;

import com.equipos.futbol_nm.persistance.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
}