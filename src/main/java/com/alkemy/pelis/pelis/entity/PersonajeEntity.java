package com.alkemy.pelis.pelis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personaje")
@Getter
@Setter
public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String nombre;

    private int edad;

    private double peso;

    private String historia;

    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<PeliculaOSerieEntity> pelisOSeries = new ArrayList<>();

    public void addPeliOSerie(PeliculaOSerieEntity p) {this.pelisOSeries.add(p);}

    public void removePeliOSerie(PeliculaOSerieEntity p) {this.pelisOSeries.remove(p);}
}
