package com.alkemy.pelis.pelis.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "peliculaOSerie")
@Getter
@Setter
@SQLDelete(sql = "UPDATE peliculaOSerie SET deleted = true where id=?")
@Where(clause = "deleted=false")
public class PeliculaOSerieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String titulo;

    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;


    @Min(1)
    @Max(5)
    private int calificacion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id", insertable = false, updatable = false)
    private GeneroEntity genero;

    @Column(name = "genero_id" , nullable = false)
    private Long generoId;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "personaje_peliculaOSerie",
            joinColumns = @JoinColumn(name = "peliculaOSerie_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private Set<PersonajeEntity> personajes = new HashSet<>();


    private boolean deleted = Boolean.FALSE;

    public void addPersonaje(PersonajeEntity p) {personajes.add(p);}

    public void removePersonaje(PersonajeEntity p) {personajes.remove(p);}

}
