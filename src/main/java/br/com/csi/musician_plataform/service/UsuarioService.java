package br.com.csi.musician_plataform.service;

import br.com.csi.musician_plataform.domain.Genero;
import br.com.csi.musician_plataform.domain.Instrumento;
import br.com.csi.musician_plataform.domain.PostBanda;
import br.com.csi.musician_plataform.domain.PostShow;
import br.com.csi.musician_plataform.domain.Usuario;
import br.com.csi.musician_plataform.model.UsuarioDTO;
import br.com.csi.musician_plataform.repos.GeneroRepository;
import br.com.csi.musician_plataform.repos.InstrumentoRepository;
import br.com.csi.musician_plataform.repos.PostBandaRepository;
import br.com.csi.musician_plataform.repos.PostShowRepository;
import br.com.csi.musician_plataform.repos.UsuarioRepository;
import br.com.csi.musician_plataform.util.NotFoundException;
import br.com.csi.musician_plataform.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final InstrumentoRepository instrumentoRepository;
    private final GeneroRepository generoRepository;
    private final PostBandaRepository postBandaRepository;
    private final PostShowRepository postShowRepository;

    public UsuarioService(final UsuarioRepository usuarioRepository,
            final InstrumentoRepository instrumentoRepository,
            final GeneroRepository generoRepository, final PostBandaRepository postBandaRepository,
            final PostShowRepository postShowRepository) {
        this.usuarioRepository = usuarioRepository;
        this.instrumentoRepository = instrumentoRepository;
        this.generoRepository = generoRepository;
        this.postBandaRepository = postBandaRepository;
        this.postShowRepository = postShowRepository;
    }

    public List<UsuarioDTO> findAll() {
        final List<Usuario> usuarios = usuarioRepository.findAll(Sort.by("id"));
        return usuarios.stream()
                .map((usuario) -> mapToDTO(usuario, new UsuarioDTO()))
                .toList();
    }

    public UsuarioDTO get(final Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UsuarioDTO usuarioDTO) {
        final Usuario usuario = new Usuario();
        mapToEntity(usuarioDTO, usuario);
        return usuarioRepository.save(usuario).getId();
    }

    public void update(final Long id, final UsuarioDTO usuarioDTO) {
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usuarioDTO, usuario);
        usuarioRepository.save(usuario);
    }

    public void delete(final Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO mapToDTO(final Usuario usuario, final UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setTelefone(usuario.getTelefone());
        usuarioDTO.setIdInstrumento(usuario.getIdInstrumento() == null ? null : usuario.getIdInstrumento().stream()
                .map(instrumento -> instrumento.getId())
                .toList());
        usuarioDTO.setIdGenero(usuario.getIdGenero() == null ? null : usuario.getIdGenero().stream()
                .map(genero -> genero.getId())
                .toList());
        return usuarioDTO;
    }

    private Usuario mapToEntity(final UsuarioDTO usuarioDTO, final Usuario usuario) {
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setTelefone(usuarioDTO.getTelefone());
        final List<Instrumento> idInstrumento = instrumentoRepository.findAllById(
                usuarioDTO.getIdInstrumento() == null ? Collections.emptyList() : usuarioDTO.getIdInstrumento());
        if (idInstrumento.size() != (usuarioDTO.getIdInstrumento() == null ? 0 : usuarioDTO.getIdInstrumento().size())) {
            throw new NotFoundException("one of idInstrumento not found");
        }
        usuario.setIdInstrumento(idInstrumento.stream().collect(Collectors.toSet()));
        final List<Genero> idGenero = generoRepository.findAllById(
                usuarioDTO.getIdGenero() == null ? Collections.emptyList() : usuarioDTO.getIdGenero());
        if (idGenero.size() != (usuarioDTO.getIdGenero() == null ? 0 : usuarioDTO.getIdGenero().size())) {
            throw new NotFoundException("one of idGenero not found");
        }
        usuario.setIdGenero(idGenero.stream().collect(Collectors.toSet()));
        return usuario;
    }

    public String getReferencedWarning(final Long id) {
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final PostBanda idUserPostBanda = postBandaRepository.findFirstByIdUser(usuario);
        if (idUserPostBanda != null) {
            return WebUtils.getMessage("usuario.postBanda.idUser.referenced", idUserPostBanda.getIdBanda());
        }
        final PostShow idUserPostShow = postShowRepository.findFirstByIdUser(usuario);
        if (idUserPostShow != null) {
            return WebUtils.getMessage("usuario.postShow.idUser.referenced", idUserPostShow.getId());
        }
        return null;
    }

}
