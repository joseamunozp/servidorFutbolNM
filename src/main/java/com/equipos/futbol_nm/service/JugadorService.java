package com.equipos.futbol_nm.service;

import com.equipos.futbol_nm.dto.JugadorConTrofeosDTO;
import com.equipos.futbol_nm.dto.TrofeoDTO;
import com.equipos.futbol_nm.persistance.model.Jugador;
import com.equipos.futbol_nm.persistance.model.Trofeo;
import com.equipos.futbol_nm.persistance.repository.JugadorRepository;
import com.equipos.futbol_nm.persistance.repository.TrofeoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JugadorService {
    private final JugadorRepository jugadorRepository;
    private final TrofeoRepository trofeoRepository;

    public JugadorService(JugadorRepository jugadorRepository, TrofeoRepository trofeoRepository) {
        this.jugadorRepository = jugadorRepository;
        this.trofeoRepository = trofeoRepository;
    }

    public Optional<Jugador> obtenerPorId(Integer id) {
        return jugadorRepository.findById(id);
    }

    public Jugador crear(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public void eliminar(Integer id) {
        jugadorRepository.deleteById(id);
    }

    public Jugador otorgarTrofeo(Integer idJugador, Integer idTrofeo) {
        Jugador jugador = jugadorRepository.findById(idJugador).orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        Trofeo trofeo = trofeoRepository.findById(idTrofeo).orElseThrow(() -> new RuntimeException("Trofeo no encontrado"));
        jugador.getTrofeos().add(trofeo);
        return jugadorRepository.save(jugador);
    }

    public List<JugadorConTrofeosDTO> obtenerJugadoresConTrofeos() {
        return jugadorRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    private JugadorConTrofeosDTO convertirADTO(Jugador jugador) {
        List<TrofeoDTO> trofeosDTO = jugador.getTrofeos().stream()
                .map(trofeo -> new TrofeoDTO(trofeo.getIdTrofeo(), trofeo.getTitulo()))
                .collect(Collectors.toList());

        return new JugadorConTrofeosDTO(jugador.getIdJugador(), jugador.getNombreUsuario(), trofeosDTO);
    }
}
