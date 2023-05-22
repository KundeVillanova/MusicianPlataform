package br.com.csi.musician_plataform.config;

import java.io.File;
import java.io.IOException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;


@Configuration
@Profile("local")
public class LocalDevConfig {
    // Construtor da classe
    public LocalDevConfig(final TemplateEngine templateEngine) throws IOException {
        // Localização do diretório raiz do projeto
        File sourceRoot = new ClassPathResource("application.properties").getFile().getParentFile();
        // Navegação até o diretório raiz do projeto (que contém o arquivo mvnw)
        while (sourceRoot.listFiles((dir, name) -> name.equals("mvnw")).length != 1) {
            sourceRoot = sourceRoot.getParentFile();
        }
        // Configuração do resolvedor de templates
        final FileTemplateResolver fileTemplateResolver = new FileTemplateResolver();
        fileTemplateResolver.setPrefix(sourceRoot.getPath() + "/src/main/resources/templates/");// Define o diretório de templates
        fileTemplateResolver.setSuffix(".html"); // Define a extensão dos templates
        fileTemplateResolver.setCacheable(false); // Desativa o cache dos templates
        fileTemplateResolver.setCharacterEncoding("UTF-8"); // Define o encoding dos templates
        fileTemplateResolver.setCheckExistence(true); // Verifica a existência dos templates
        // Configuração do TemplateEngine
        templateEngine.setTemplateResolver(fileTemplateResolver);
    }

}
