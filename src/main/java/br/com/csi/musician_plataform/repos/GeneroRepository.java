package br.com.csi.musician_plataform.repos;

import br.com.csi.musician_plataform.domain.Genero;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
