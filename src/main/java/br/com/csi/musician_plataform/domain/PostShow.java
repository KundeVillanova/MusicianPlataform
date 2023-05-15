package br.com.csi.musician_plataform.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class PostShow {

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
    private String titulo;

    @Column
    private String descricao;

    @Column
    private LocalDate data;

    @Column
    private LocalTime hora;

    @Column
    private String ingressos;

    @Column
    private String localizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_id")
    private Usuario idUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero_id")
    private Genero idGenero;


}
