package com.samuelm.helpdesk.config;

import com.samuelm.helpdesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService service;

    @Bean
    public void instanciaDB(){
        this.service.instaciaDB();
    }
}
