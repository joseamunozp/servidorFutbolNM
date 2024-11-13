package com.equipos.futbol_nm.service;

import com.equipos.futbol_nm.dto.JugadorDTO;
import com.equipos.futbol_nm.dto.TrofeoConJugadoresDTO;
import com.equipos.futbol_nm.persistance.model.Trofeo;
import com.equipos.futbol_nm.persistance.repository.TrofeoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrofeoService {
    private final TrofeoRepository trofeoRepository;

    public TrofeoService(TrofeoRepository trofeoRepository) {
        this.trofeoRepository = trofeoRepository;
    }

    public Trofeo crear(Trofeo trofeo) {
        return trofeoRepository.save(trofeo);
    }

    public void eliminar(Integer id) {
        trofeoRepository.deleteById(id);
    }

    public List<TrofeoConJugadoresDTO> obtenerTrofeosConJugadores() {
        return trofeoRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    private TrofeoConJugadoresDTO convertirADTO(Trofeo trofeo) {
        List<JugadorDTO> jugadoresDTO = trofeo.getJugadores().stream()
                .map(jugador -> new JugadorDTO(jugador.getIdJugador(), jugador.getNombreUsuario()))
                .collect(Collectors.toList());

        return new TrofeoConJugadoresDTO(
                trofeo.getIdTrofeo(),
                trofeo.getTitulo(),
                trofeo.getDescripcion(),
                jugadoresDTO
        );
    }
}
