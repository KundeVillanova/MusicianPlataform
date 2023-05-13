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
import java.time.LocalDate;
import java.time.LocalTime;


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

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(final LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(final LocalTime hora) {
        this.hora = hora;
    }

    public String getIngressos() {
        return ingressos;
    }

    public void setIngressos(final String ingressos) {
        this.ingressos = ingressos;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(final String localizacao) {
        this.localizacao = localizacao;
    }

    public Usuario getIdUser() {
        return idUser;
    }

    public void setIdUser(final Usuario idUser) {
        this.idUser = idUser;
    }

    public Genero getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(final Genero idGenero) {
        this.idGenero = idGenero;
    }

}
