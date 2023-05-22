package br.com.csi.musician_plataform.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JacksonConfig {
    //configuração do Jackson para personalizar a serialização e desserialização de objetos JSON
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return jacksonObjectMapperBuilder -> {
                jacksonObjectMapperBuilder.featuresToDisable(
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, // Não falhar em propriedades desconhecidas
                        DeserializationFeature.ACCEPT_FLOAT_AS_INT, // Não aceitar floats como inteiros
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Serializar datas como timestamps
                };
    }

}
