package com.alkemy.pelis.pelis.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personaje")
@Getter
@Setter
@SQLDelete(sql = "UPDATE personaje SET deleted = true where id=?")
@Where(clause = "deleted=false")
public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String nombre;

    private int edad;

    private double peso;

    private String historia;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<PeliculaOSerieEntity> pelisOSeries = new ArrayList<>();

    public void addPeliOSerie(PeliculaOSerieEntity p) {this.pelisOSeries.add(p);}

    public void removePeliOSerie(PeliculaOSerieEntity p) {this.pelisOSeries.remove(p);}
}
