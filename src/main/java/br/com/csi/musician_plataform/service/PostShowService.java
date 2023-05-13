package br.com.csi.musician_plataform.service;

import br.com.csi.musician_plataform.domain.Genero;
import br.com.csi.musician_plataform.domain.PostShow;
import br.com.csi.musician_plataform.domain.Usuario;
import br.com.csi.musician_plataform.model.PostShowDTO;
import br.com.csi.musician_plataform.repos.GeneroRepository;
import br.com.csi.musician_plataform.repos.PostShowRepository;
import br.com.csi.musician_plataform.repos.UsuarioRepository;
import br.com.csi.musician_plataform.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PostShowService {

    private final PostShowRepository postShowRepository;
    private final UsuarioRepository usuarioRepository;
    private final GeneroRepository generoRepository;

    public PostShowService(final PostShowRepository postShowRepository,
            final UsuarioRepository usuarioRepository, final GeneroRepository generoRepository) {
        this.postShowRepository = postShowRepository;
        this.usuarioRepository = usuarioRepository;
        this.generoRepository = generoRepository;
    }

    public List<PostShowDTO> findAll() {
        final List<PostShow> postShows = postShowRepository.findAll(Sort.by("id"));
        return postShows.stream()
                .map((postShow) -> mapToDTO(postShow, new PostShowDTO()))
                .toList();
    }

    public PostShowDTO get(final Long id) {
        return postShowRepository.findById(id)
                .map(postShow -> mapToDTO(postShow, new PostShowDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final PostShowDTO postShowDTO) {
        final PostShow postShow = new PostShow();
        mapToEntity(postShowDTO, postShow);
        return postShowRepository.save(postShow).getId();
    }

    public void update(final Long id, final PostShowDTO postShowDTO) {
        final PostShow postShow = postShowRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(postShowDTO, postShow);
        postShowRepository.save(postShow);
    }

    public void delete(final Long id) {
        postShowRepository.deleteById(id);
    }

    private PostShowDTO mapToDTO(final PostShow postShow, final PostShowDTO postShowDTO) {
        postShowDTO.setId(postShow.getId());
        postShowDTO.setTitulo(postShow.getTitulo());
        postShowDTO.setDescricao(postShow.getDescricao());
        postShowDTO.setData(postShow.getData());
        postShowDTO.setHora(postShow.getHora());
        postShowDTO.setIngressos(postShow.getIngressos());
        postShowDTO.setLocalizacao(postShow.getLocalizacao());
        postShowDTO.setIdUser(postShow.getIdUser() == null ? null : postShow.getIdUser().getId());
        postShowDTO.setIdGenero(postShow.getIdGenero() == null ? null : postShow.getIdGenero().getId());
        return postShowDTO;
    }

    private PostShow mapToEntity(final PostShowDTO postShowDTO, final PostShow postShow) {
        postShow.setTitulo(postShowDTO.getTitulo());
        postShow.setDescricao(postShowDTO.getDescricao());
        postShow.setData(postShowDTO.getData());
        postShow.setHora(postShowDTO.getHora());
        postShow.setIngressos(postShowDTO.getIngressos());
        postShow.setLocalizacao(postShowDTO.getLocalizacao());
        final Usuario idUser = postShowDTO.getIdUser() == null ? null : usuarioRepository.findById(postShowDTO.getIdUser())
                .orElseThrow(() -> new NotFoundException("idUser not found"));
        postShow.setIdUser(idUser);
        final Genero idGenero = postShowDTO.getIdGenero() == null ? null : generoRepository.findById(postShowDTO.getIdGenero())
                .orElseThrow(() -> new NotFoundException("idGenero not found"));
        postShow.setIdGenero(idGenero);
        return postShow;
    }

}
