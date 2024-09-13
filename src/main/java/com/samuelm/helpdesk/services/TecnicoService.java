package com.samuelm.helpdesk.services;

import com.samuelm.helpdesk.domain.Tecnico;
import com.samuelm.helpdesk.dtos.TecnicoDTO;
import com.samuelm.helpdesk.repositories.TecnicoRepository;
import com.samuelm.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!  Id: " + id));
    }
    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO dto) {
        dto.setId(null);
        Tecnico tecnico = new Tecnico();
        BeanUtils.copyProperties(dto, tecnico);
        return repository.save(tecnico);
    }
}
