package br.com.csi.musician_plataform.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;


public class PostShowDTO {

    private Long id;

    @Size(max = 255)
    private String titulo;

    @Size(max = 255)
    private String descricao;

    private LocalDate data;

    @Schema(type = "string", example = "18:30")
    private LocalTime hora;

    @Size(max = 255)
    private String ingressos;

    @Size(max = 255)
    private String localizacao;

    private Long idUser;

    private Long idGenero;

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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(final Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(final Long idGenero) {
        this.idGenero = idGenero;
    }

}
