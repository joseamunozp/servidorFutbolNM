package com.equipos.futbol_nm.persistance.repository;

import com.equipos.futbol_nm.persistance.model.Trofeo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrofeoRepository extends JpaRepository<Trofeo, Integer> {
}