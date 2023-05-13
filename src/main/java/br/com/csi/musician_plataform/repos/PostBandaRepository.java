package br.com.csi.musician_plataform.repos;

import br.com.csi.musician_plataform.domain.Instrumento;
import br.com.csi.musician_plataform.domain.PostBanda;
import br.com.csi.musician_plataform.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostBandaRepository extends JpaRepository<PostBanda, Long> {

    PostBanda findFirstByIdUser(Usuario usuario);

    PostBanda findFirstByIdInstrumento(Instrumento instrumento);

}
