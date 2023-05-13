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
import java.util.Set;


@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(final String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    public Set<Instrumento> getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(final Set<Instrumento> idInstrumento) {
        this.idInstrumento = idInstrumento;
    }

    public Set<Genero> getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(final Set<Genero> idGenero) {
        this.idGenero = idGenero;
    }

    public Set<PostBanda> getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(final Set<PostBanda> idBanda) {
        this.idBanda = idBanda;
    }

    public Set<PostShow> getIdShow() {
        return idShow;
    }

    public void setIdShow(final Set<PostShow> idShow) {
        this.idShow = idShow;
    }

}
