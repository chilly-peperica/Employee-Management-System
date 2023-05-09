package com.jainva.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class UtilityConfigurations {

    @Bean
    public ObjectMapper createObjectMapper(){
        return new ObjectMapper();
    }
}
