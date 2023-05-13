package br.com.csi.musician_plataform.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import java.util.Set;


@Entity
public class PostBanda {

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
    private Long idBanda;

    @Column
    private String titulo;

    @Column
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_id")
    private Usuario idUser;

    @ManyToMany
    @JoinTable(
            name = "vaga",
            joinColumns = @JoinColumn(name = "id_banda"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Instrumento> idInstrumento;

    public Long getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(final Long idBanda) {
        this.idBanda = idBanda;
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

    public Usuario getIdUser() {
        return idUser;
    }

    public void setIdUser(final Usuario idUser) {
        this.idUser = idUser;
    }

    public Set<Instrumento> getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(final Set<Instrumento> idInstrumento) {
        this.idInstrumento = idInstrumento;
    }

}
