package br.com.csi.musician_plataform.repos;

import br.com.csi.musician_plataform.domain.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InstrumentoRepository extends JpaRepository<Instrumento, Long> {
}
