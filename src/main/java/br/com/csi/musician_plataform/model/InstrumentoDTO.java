package br.com.csi.musician_plataform.model;

import jakarta.validation.constraints.Size;


public class InstrumentoDTO {

    private Long id;

    @Size(max = 255)
    private String nomeInstrumento;

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

}
