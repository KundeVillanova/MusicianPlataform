package br.com.csi.musician_plataform.repos;

import br.com.csi.musician_plataform.domain.Genero;
import br.com.csi.musician_plataform.domain.PostShow;
import br.com.csi.musician_plataform.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostShowRepository extends JpaRepository<PostShow, Long> {

    PostShow findFirstByIdUser(Usuario usuario);

    PostShow findFirstByIdGenero(Genero genero);

}
