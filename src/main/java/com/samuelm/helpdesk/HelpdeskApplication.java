package com.samuelm.helpdesk;

import com.samuelm.helpdesk.domain.Chamado;
import com.samuelm.helpdesk.domain.Cliente;
import com.samuelm.helpdesk.domain.Tecnico;
import com.samuelm.helpdesk.domain.enums.Perfil;
import com.samuelm.helpdesk.domain.enums.Prioridade;
import com.samuelm.helpdesk.domain.enums.Status;
import com.samuelm.helpdesk.repositories.ChamadoRepository;
import com.samuelm.helpdesk.repositories.ClienteRepository;
import com.samuelm.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public static void main(String[] args) {
        SpringApplication.run(HelpdeskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Tecnico tecnico1 = new Tecnico(null, "Samuel", "38037277070", "samuel@gmail.com","123");
        tecnico1.addPerfil(Perfil.ADMIN);

        Cliente cliente1 = new Cliente(null, "Samuel", "08128874098", "samuel2@gmail.com", "234");

        Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tecnico1, cliente1);

        tecnicoRepository.saveAll(Arrays.asList(tecnico1));
        clienteRepository.saveAll(Arrays.asList(cliente1));
        chamadoRepository.saveAll(Arrays.asList(chamado1));


    }
}
