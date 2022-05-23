package com.alkemy.pelis.pelis.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "genero")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genero SET deleted = true where id=?")
@Where(clause = "deleted=false")
public class GeneroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    private String imagen;

    private boolean deleted = Boolean.FALSE;
}
