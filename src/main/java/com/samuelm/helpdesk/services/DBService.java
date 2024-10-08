package com.samuelm.helpdesk.services;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instaciaDB(){

        Tecnico tecnico1 = new Tecnico(null, "Samuel", "601.216.170-09", "samuel@gmail.com",encoder.encode(encoder.encode("123")));
        Tecnico tecnico2 = new Tecnico(null, "Vinicius", "611.505.080-43", "vinicius@gmail.com",encoder.encode("123"));
        Tecnico tecnico3 = new Tecnico(null, "Leticia", "322.671.730-85", "leticia@gmail.com",encoder.encode("123"));
        Tecnico tecnico4 = new Tecnico(null, "Bruno", "700.333.000-89", "hruno@gmail.com",encoder.encode("123"));
        Tecnico tecnico5 = new Tecnico(null, "Henrique", "595.260.880-93", "henrique@gmail.com",encoder.encode("123"));
        Tecnico tecnico6 = new Tecnico(null, "Juliano", "623.164.660-28", "juliano@gmail.com",encoder.encode("123"));

        tecnico1.addPerfil(Perfil.ADMIN);
        tecnico2.addPerfil(Perfil.TECNICO);
        tecnico3.addPerfil(Perfil.ADMIN);
        tecnico4.addPerfil(Perfil.TECNICO);
        tecnico5.addPerfil(Perfil.TECNICO);
        Cliente cliente1 = new Cliente(null, "Samuel", "479.701.920-41", "samuel2@gmail.com", encoder.encode("234"));
        Cliente cliente2 = new Cliente(null, "Matheus", "882.013.400-43", "matheus@gmail.com", encoder.encode("234"));
        Cliente cliente3 = new Cliente(null, "Lorenço", "650.118.140-25", "lorenco@gmail.com", encoder.encode("234"));
        Cliente cliente4 = new Cliente(null, "João Lucas", "780.031.780-37", "joao@gmail.com", encoder.encode("234"));
        Cliente cliente5 = new Cliente(null, "Guizzo", "623.348.960-12", "guizzo@gmail.com", encoder.encode("234"));
        Cliente cliente6 = new Cliente(null, "Rafael", "104.083.020-03", "rafael@gmail.com", encoder.encode("234"));

        cliente1.addPerfil(Perfil.ADMIN);
        cliente2.addPerfil(Perfil.ADMIN);
        cliente3.addPerfil(Perfil.ADMIN);

        Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tecnico1, cliente1);
        Chamado chamado2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 02", "Segundo Chamado", tecnico5, cliente1);
        Chamado chamado3 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 03", "Terceiro Chamado", tecnico3, cliente3);
        Chamado chamado4 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 04", "Quarto Chamado", tecnico1, cliente2);
        Chamado chamado5 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado 05", "Quinto Chamado", tecnico4, cliente5);
        Chamado chamado6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 06", "Sexto Chamado", tecnico5, cliente3);
        Chamado chamado7 = new Chamado(null, Prioridade.ALTA, Status.ENCERRADO, "Chamado 07", "Sétimo Chamado", tecnico6, cliente6);
        tecnicoRepository.saveAll(Arrays.asList(tecnico1, tecnico2, tecnico3, tecnico4,tecnico5, tecnico6));
        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3, cliente4, cliente5, cliente6));
        chamadoRepository.saveAll(Arrays.asList(chamado1, chamado2, chamado3, chamado4, chamado5, chamado6, chamado7));

    }
}
