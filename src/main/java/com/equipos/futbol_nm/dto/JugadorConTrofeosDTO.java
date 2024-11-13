package com.equipos.futbol_nm.dto;

import java.util.List;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class JugadorConTrofeosDTO {
    private int idJugador;
    private String nombreUsuario;
    private List<TrofeoDTO> trofeos;
}
