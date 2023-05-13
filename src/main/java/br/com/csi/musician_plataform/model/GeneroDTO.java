package br.com.csi.musician_plataform.model;

import jakarta.validation.constraints.Size;


public class GeneroDTO {

    private Long id;

    @Size(max = 255)
    private String generoMusical;

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

}
