package br.com.csi.musician_plataform.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.Set;


@Entity
public class Genero {

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
    private String generoMusical;

    @ManyToMany(mappedBy = "idGenero")
    private Set<Usuario> idUser;

    @OneToMany(mappedBy = "idGenero")
    private Set<PostShow> idShow;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(final String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public Set<Usuario> getIdUser() {
        return idUser;
    }

    public void setIdUser(final Set<Usuario> idUser) {
        this.idUser = idUser;
    }

    public Set<PostShow> getIdShow() {
        return idShow;
    }

    public void setIdShow(final Set<PostShow> idShow) {
        this.idShow = idShow;
    }

}
