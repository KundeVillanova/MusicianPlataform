package br.com.csi.musician_plataform.service;

import br.com.csi.musician_plataform.domain.Instrumento;
import br.com.csi.musician_plataform.domain.PostBanda;
import br.com.csi.musician_plataform.domain.Usuario;
import br.com.csi.musician_plataform.model.PostBandaDTO;
import br.com.csi.musician_plataform.repos.InstrumentoRepository;
import br.com.csi.musician_plataform.repos.PostBandaRepository;
import br.com.csi.musician_plataform.repos.UsuarioRepository;
import br.com.csi.musician_plataform.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class PostBandaService {

    private final PostBandaRepository postBandaRepository;
    private final UsuarioRepository usuarioRepository;
    private final InstrumentoRepository instrumentoRepository;

    public PostBandaService(final PostBandaRepository postBandaRepository,
            final UsuarioRepository usuarioRepository,
            final InstrumentoRepository instrumentoRepository) {
        this.postBandaRepository = postBandaRepository;
        this.usuarioRepository = usuarioRepository;
        this.instrumentoRepository = instrumentoRepository;
    }

    public List<PostBandaDTO> findAll() {
        final List<PostBanda> postBandas = postBandaRepository.findAll(Sort.by("idBanda"));
        return postBandas.stream()
                .map((postBanda) -> mapToDTO(postBanda, new PostBandaDTO()))
                .toList();
    }

    public PostBandaDTO get(final Long idBanda) {
        return postBandaRepository.findById(idBanda)
                .map(postBanda -> mapToDTO(postBanda, new PostBandaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final PostBandaDTO postBandaDTO) {
        final PostBanda postBanda = new PostBanda();
        mapToEntity(postBandaDTO, postBanda);
        return postBandaRepository.save(postBanda).getIdBanda();
    }

    public void update(final Long idBanda, final PostBandaDTO postBandaDTO) {
        final PostBanda postBanda = postBandaRepository.findById(idBanda)
                .orElseThrow(NotFoundException::new);
        mapToEntity(postBandaDTO, postBanda);
        postBandaRepository.save(postBanda);
    }

    public void delete(final Long idBanda) {
        postBandaRepository.deleteById(idBanda);
    }

    private PostBandaDTO mapToDTO(final PostBanda postBanda, final PostBandaDTO postBandaDTO) {
        postBandaDTO.setIdBanda(postBanda.getIdBanda());
        postBandaDTO.setTitulo(postBanda.getTitulo());
        postBandaDTO.setDescricao(postBanda.getDescricao());
        postBandaDTO.setIdUser(postBanda.getIdUser() == null ? null : postBanda.getIdUser().getId());
        postBandaDTO.setIdInstrumento(postBanda.getIdInstrumento() == null ? null : postBanda.getIdInstrumento().stream()
                .map(instrumento -> instrumento.getId())
                .toList());
        return postBandaDTO;
    }

    private PostBanda mapToEntity(final PostBandaDTO postBandaDTO, final PostBanda postBanda) {
        postBanda.setTitulo(postBandaDTO.getTitulo());
        postBanda.setDescricao(postBandaDTO.getDescricao());
        final Usuario idUser = postBandaDTO.getIdUser() == null ? null : usuarioRepository.findById(postBandaDTO.getIdUser())
                .orElseThrow(() -> new NotFoundException("idUser not found"));
        postBanda.setIdUser(idUser);
        final List<Instrumento> idInstrumento = instrumentoRepository.findAllById(
                postBandaDTO.getIdInstrumento() == null ? Collections.emptyList() : postBandaDTO.getIdInstrumento());
        if (idInstrumento.size() != (postBandaDTO.getIdInstrumento() == null ? 0 : postBandaDTO.getIdInstrumento().size())) {
            throw new NotFoundException("one of idInstrumento not found");
        }
        postBanda.setIdInstrumento(idInstrumento.stream().collect(Collectors.toSet()));
        return postBanda;
    }

}
