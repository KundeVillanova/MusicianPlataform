package br.com.csi.musician_plataform.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("br.com.csi.musician_plataform.domain")
@EnableJpaRepositories("br.com.csi.musician_plataform.repos")
@EnableTransactionManagement
public class DomainConfig {
}
