package com.samuelm.helpdesk.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samuelm.helpdesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService service;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    public boolean instanciaDB(){
        if(value.equals("create")){
            this.service.instaciaDB();
        }
        return false;
    }
}
