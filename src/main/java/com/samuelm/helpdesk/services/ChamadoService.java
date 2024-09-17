package com.samuelm.helpdesk.services;

import com.samuelm.helpdesk.domain.Chamado;
import com.samuelm.helpdesk.domain.Cliente;
import com.samuelm.helpdesk.domain.Tecnico;
import com.samuelm.helpdesk.domain.enums.Prioridade;
import com.samuelm.helpdesk.domain.enums.Status;
import com.samuelm.helpdesk.dtos.ChamadoDTO;
import com.samuelm.helpdesk.repositories.ChamadoRepository;
import com.samuelm.helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Chamado> findAll(){
        return repository.findAll();
    }

    public Chamado create(@Valid ChamadoDTO dto) {
        return repository.save(newChamado(dto));
    }

    private Chamado newChamado(ChamadoDTO dto){
        Tecnico tecnico = tecnicoService.findById(dto.getTecnico());
        Cliente cliente = clienteService.findById(dto.getCliente());

        Chamado chamado = new Chamado();
        if(dto.getId() != null){
            chamado.setId(dto.getId());
        }
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
        chamado.setStatus(Status.toEnum(dto.getStatus()));
        chamado.setTitulo(dto.getTitulo());
        chamado.setObservacoes(dto.getObservacoes());
        return chamado;

    }
}
