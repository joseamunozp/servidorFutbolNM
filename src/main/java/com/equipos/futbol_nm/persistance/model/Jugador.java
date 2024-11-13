package com.equipos.futbol_nm.persistance.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJugador;

    @Column(nullable = false, unique = true)
    private String nombreUsuario;

    @ManyToMany(
        fetch = FetchType.EAGER, 
        cascade = CascadeType.ALL
    )
    @JoinTable(
        name = "players_has_trophies",
        joinColumns = @JoinColumn(name = "players_idplayer"),
        inverseJoinColumns = @JoinColumn(name = "trophies_idtrophie")
    )
    private List<Trofeo> trofeos;
}
