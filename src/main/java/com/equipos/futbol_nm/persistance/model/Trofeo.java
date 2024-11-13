package com.equipos.futbol_nm.persistance.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "trophies")
public class Trofeo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTrofeo;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @ManyToMany(
        mappedBy = "trofeos",
        fetch = FetchType.EAGER, 
        cascade = CascadeType.ALL
    )
    private List<Jugador> jugadores;
}
