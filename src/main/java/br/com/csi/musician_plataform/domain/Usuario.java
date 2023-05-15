package br.com.csi.musician_plataform.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String senha;

    @Column
    private String telefone;

    @ManyToMany
    @JoinTable(
            name = "toca",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "instrumento_id")
    )
    private Set<Instrumento> idInstrumento;

    @ManyToMany
    @JoinTable(
            name = "genero_favorito",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private Set<Genero> idGenero;

    @OneToMany(mappedBy = "idUser")
    private Set<PostBanda> idBanda;

    @OneToMany(mappedBy = "idUser")
    private Set<PostShow> idShow;


}
