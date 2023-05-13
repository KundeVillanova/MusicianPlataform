package br.com.csi.musician_plataform.repos;

import br.com.csi.musician_plataform.domain.Genero;
import br.com.csi.musician_plataform.domain.Instrumento;
import br.com.csi.musician_plataform.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findFirstByIdInstrumento(Instrumento instrumento);

    Usuario findFirstByIdGenero(Genero genero);

}
