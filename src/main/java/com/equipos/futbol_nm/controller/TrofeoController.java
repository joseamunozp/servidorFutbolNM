package com.equipos.futbol_nm.controller;

import com.equipos.futbol_nm.dto.TrofeoConJugadoresDTO;
import com.equipos.futbol_nm.persistance.model.Trofeo;
import com.equipos.futbol_nm.service.TrofeoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trofeos")
public class TrofeoController {
    private final TrofeoService trofeoService;

    public TrofeoController(TrofeoService trofeoService) {
        this.trofeoService = trofeoService;
    }

    @PostMapping
    public Trofeo crear(@RequestBody Trofeo trofeo) {
        return trofeoService.crear(trofeo);
    }

        @GetMapping
    public List<TrofeoConJugadoresDTO> listarTrofeosConJugadores() {
        return trofeoService.obtenerTrofeosConJugadores();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        trofeoService.eliminar(id);
    }
}
