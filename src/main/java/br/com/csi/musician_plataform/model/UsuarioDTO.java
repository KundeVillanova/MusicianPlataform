package br.com.csi.musician_plataform.model;

import jakarta.validation.constraints.Size;
import java.util.List;


public class UsuarioDTO {

    private Long id;

    @Size(max = 255)
    private String nome;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String senha;

    @Size(max = 255)
    private String telefone;

    private List<Long> idInstrumento;

    private List<Long> idGenero;

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

    public List<Long> getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(final List<Long> idInstrumento) {
        this.idInstrumento = idInstrumento;
    }

    public List<Long> getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(final List<Long> idGenero) {
        this.idGenero = idGenero;
    }

}
