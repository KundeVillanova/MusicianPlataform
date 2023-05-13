package br.com.csi.musician_plataform.rest;

import br.com.csi.musician_plataform.model.GeneroDTO;
import br.com.csi.musician_plataform.service.GeneroService;
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
@RequestMapping(value = "/api/generos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneroResource {

    private final GeneroService generoService;

    public GeneroResource(final GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAllGeneros() {
        return ResponseEntity.ok(generoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> getGenero(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(generoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createGenero(@RequestBody @Valid final GeneroDTO generoDTO) {
        final Long createdId = generoService.create(generoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGenero(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final GeneroDTO generoDTO) {
        generoService.update(id, generoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteGenero(@PathVariable(name = "id") final Long id) {
        generoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
