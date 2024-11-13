package com.equipos.futbol_nm.dto;

import java.util.List;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class TrofeoConJugadoresDTO {
    private int idTrofeo;
    private String titulo;
    private String descripcion;
    private List<JugadorDTO> jugadores;
}
