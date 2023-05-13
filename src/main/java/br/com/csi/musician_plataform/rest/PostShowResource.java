package br.com.csi.musician_plataform.rest;

import br.com.csi.musician_plataform.model.PostShowDTO;
import br.com.csi.musician_plataform.service.PostShowService;
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
@RequestMapping(value = "/api/postShows", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostShowResource {

    private final PostShowService postShowService;

    public PostShowResource(final PostShowService postShowService) {
        this.postShowService = postShowService;
    }

    @GetMapping
    public ResponseEntity<List<PostShowDTO>> getAllPostShows() {
        return ResponseEntity.ok(postShowService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostShowDTO> getPostShow(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(postShowService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createPostShow(@RequestBody @Valid final PostShowDTO postShowDTO) {
        final Long createdId = postShowService.create(postShowDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePostShow(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final PostShowDTO postShowDTO) {
        postShowService.update(id, postShowDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePostShow(@PathVariable(name = "id") final Long id) {
        postShowService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
