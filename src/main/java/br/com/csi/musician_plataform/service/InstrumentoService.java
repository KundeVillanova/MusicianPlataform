package br.com.csi.musician_plataform.service;

import br.com.csi.musician_plataform.domain.Instrumento;
import br.com.csi.musician_plataform.domain.PostBanda;
import br.com.csi.musician_plataform.domain.Usuario;
import br.com.csi.musician_plataform.model.InstrumentoDTO;
import br.com.csi.musician_plataform.repos.InstrumentoRepository;
import br.com.csi.musician_plataform.repos.PostBandaRepository;
import br.com.csi.musician_plataform.repos.UsuarioRepository;
import br.com.csi.musician_plataform.util.NotFoundException;
import br.com.csi.musician_plataform.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class InstrumentoService {

    private final InstrumentoRepository instrumentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PostBandaRepository postBandaRepository;

    public InstrumentoService(final InstrumentoRepository instrumentoRepository, final UsuarioRepository usuarioRepository,
            final PostBandaRepository postBandaRepository)
    {
        this.instrumentoRepository = instrumentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.postBandaRepository = postBandaRepository;
    }

    public List<InstrumentoDTO> findAll() {
        final List<Instrumento> instrumentos = instrumentoRepository.findAll(Sort.by("id"));
        return instrumentos.stream()
                .map((instrumento) -> mapToDTO(instrumento, new InstrumentoDTO())).toList();
    }

    public InstrumentoDTO get(final Long id) {
        return instrumentoRepository.findById(id)
           .map(instrumento -> mapToDTO(instrumento, new InstrumentoDTO()))
           .orElseThrow(NotFoundException::new);
    }

    public Long create(final InstrumentoDTO instrumentoDTO) {
        final Instrumento instrumento = new Instrumento();
        mapToEntity(instrumentoDTO, instrumento);
        return instrumentoRepository.save(instrumento).getId();
    }

    public void update(final Long id, final InstrumentoDTO instrumentoDTO) {
        final Instrumento instrumento = instrumentoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(instrumentoDTO, instrumento);
        instrumentoRepository.save(instrumento);
    }

    public void delete(final Long id) {
        instrumentoRepository.deleteById(id);
    }

    private InstrumentoDTO mapToDTO(final Instrumento instrumento,
            final InstrumentoDTO instrumentoDTO) {
        instrumentoDTO.setId(instrumento.getId());
        instrumentoDTO.setNomeInstrumento(instrumento.getNomeInstrumento());
        return instrumentoDTO;
    }

    private Instrumento mapToEntity(final InstrumentoDTO instrumentoDTO,
            final Instrumento instrumento) {
        instrumento.setNomeInstrumento(instrumentoDTO.getNomeInstrumento());
        return instrumento;
    }

    public String getReferencedWarning(final Long id) {
        final Instrumento instrumento = instrumentoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Usuario idInstrumentoUsuario = usuarioRepository.findFirstByIdInstrumento(instrumento);
        if (idInstrumentoUsuario != null) {
            return WebUtils.getMessage("instrumento.usuario.idInstrumento.referenced", idInstrumentoUsuario.getId());
        }
        final PostBanda idInstrumentoPostBanda = postBandaRepository.findFirstByIdInstrumento(instrumento);
        if (idInstrumentoPostBanda != null) {
            return WebUtils.getMessage("instrumento.postBanda.idInstrumento.referenced", idInstrumentoPostBanda.getIdBanda());
        }
        return null;
    }

}
