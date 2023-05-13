package br.com.csi.musician_plataform.model;

import jakarta.validation.constraints.Size;
import java.util.List;


public class PostBandaDTO {

    private Long idBanda;

    @Size(max = 255)
    private String titulo;

    @Size(max = 255)
    private String descricao;

    private Long idUser;

    private List<Long> idInstrumento;

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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(final Long idUser) {
        this.idUser = idUser;
    }

    public List<Long> getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(final List<Long> idInstrumento) {
        this.idInstrumento = idInstrumento;
    }

}
