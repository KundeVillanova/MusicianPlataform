package br.com.csi.musician_plataform.service;

import br.com.csi.musician_plataform.domain.Genero;
import br.com.csi.musician_plataform.domain.PostShow;
import br.com.csi.musician_plataform.domain.Usuario;
import br.com.csi.musician_plataform.model.GeneroDTO;
import br.com.csi.musician_plataform.repos.GeneroRepository;
import br.com.csi.musician_plataform.repos.PostShowRepository;
import br.com.csi.musician_plataform.repos.UsuarioRepository;
import br.com.csi.musician_plataform.util.NotFoundException;
import br.com.csi.musician_plataform.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class GeneroService {

    private final GeneroRepository generoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PostShowRepository postShowRepository;

    public GeneroService(final GeneroRepository generoRepository,
            final UsuarioRepository usuarioRepository,
            final PostShowRepository postShowRepository) {
        this.generoRepository = generoRepository;
        this.usuarioRepository = usuarioRepository;
        this.postShowRepository = postShowRepository;
    }

    public List<GeneroDTO> findAll() {
        final List<Genero> generos = generoRepository.findAll(Sort.by("id"));
        return generos.stream()
                .map((genero) -> mapToDTO(genero, new GeneroDTO())).toList();
    }

    public GeneroDTO get(final Long id) {
        return generoRepository.findById(id)
                .map(genero -> mapToDTO(genero, new GeneroDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final GeneroDTO generoDTO) {
        final Genero genero = new Genero();
        mapToEntity(generoDTO, genero);
        return generoRepository.save(genero).getId();
    }

    public void update(final Long id, final GeneroDTO generoDTO) {
        final Genero genero = generoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(generoDTO, genero);
        generoRepository.save(genero);
    }

    public void delete(final Long id) {
        generoRepository.deleteById(id);
    }

    private GeneroDTO mapToDTO(final Genero genero, final GeneroDTO generoDTO) {
        generoDTO.setId(genero.getId());
        generoDTO.setGeneroMusical(genero.getGeneroMusical());
        return generoDTO;
    }

    private Genero mapToEntity(final GeneroDTO generoDTO, final Genero genero) {
        genero.setGeneroMusical(generoDTO.getGeneroMusical());
        return genero;
    }

    public String getReferencedWarning(final Long id) {
        final Genero genero = generoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Usuario idGeneroUsuario = usuarioRepository.findFirstByIdGenero(genero);
        if (idGeneroUsuario != null) {
            return WebUtils.getMessage("genero.usuario.idGenero.referenced", idGeneroUsuario.getId());
        }
        final PostShow idGeneroPostShow = postShowRepository.findFirstByIdGenero(genero);
        if (idGeneroPostShow != null) {
            return WebUtils.getMessage("genero.postShow.idGenero.referenced", idGeneroPostShow.getId());
        }
        return null;
    }

}
