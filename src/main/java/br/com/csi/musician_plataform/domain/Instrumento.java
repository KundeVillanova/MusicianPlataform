package br.com.csi.musician_plataform.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.Set;


@Entity
public class Instrumento {

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
    private String nomeInstrumento;

    @ManyToMany(mappedBy = "idInstrumento")
    private Set<Usuario> idUsuario;

    @ManyToMany(mappedBy = "idInstrumento")
    private Set<PostBanda> idBanda;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNomeInstrumento() {
        return nomeInstrumento;
    }

    public void setNomeInstrumento(final String nomeInstrumento) {
        this.nomeInstrumento = nomeInstrumento;
    }

    public Set<Usuario> getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final Set<Usuario> idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Set<PostBanda> getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(final Set<PostBanda> idBanda) {
        this.idBanda = idBanda;
    }

}
