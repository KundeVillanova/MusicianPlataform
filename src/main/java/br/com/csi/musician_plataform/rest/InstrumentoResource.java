package br.com.csi.musician_plataform.rest;

import br.com.csi.musician_plataform.model.InstrumentoDTO;
import br.com.csi.musician_plataform.service.InstrumentoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/instrumentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstrumentoResource {

    private final InstrumentoService instrumentoService;

    public InstrumentoResource(final InstrumentoService instrumentoService) {
        this.instrumentoService = instrumentoService;
    }

    @GetMapping
    public ResponseEntity<List<InstrumentoDTO>> getAllInstrumentos() {
        return ResponseEntity.ok(instrumentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrumentoDTO> getInstrumento(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(instrumentoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createInstrumento(
            @RequestBody @Valid final InstrumentoDTO instrumentoDTO) {
        final Long createdId = instrumentoService.create(instrumentoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInstrumento(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final InstrumentoDTO instrumentoDTO) {
        instrumentoService.update(id, instrumentoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteInstrumento(@PathVariable(name = "id") final Long id) {
        instrumentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
