package com.equipos.futbol_nm.controller;

import com.equipos.futbol_nm.dto.JugadorConTrofeosDTO;
import com.equipos.futbol_nm.dto.JugadorDTO;
import com.equipos.futbol_nm.persistance.model.Jugador;
import com.equipos.futbol_nm.service.JugadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {
    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping
    public List<JugadorConTrofeosDTO> listarJugadoresConTrofeos() {
        return jugadorService.obtenerJugadoresConTrofeos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> obtenerPorId(@PathVariable Integer id) {
        return jugadorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Jugador crear(@RequestBody Jugador jugador) {
        return jugadorService.crear(jugador);
    }

    @PostMapping("/{idJugador}/trofeos/{idTrofeo}")
    public Jugador otorgarTrofeo(@PathVariable Integer idJugador, @PathVariable Integer idTrofeo) {
        return jugadorService.otorgarTrofeo(idJugador, idTrofeo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        jugadorService.eliminar(id);
    }
}
