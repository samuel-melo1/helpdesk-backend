package com.samuelm.helpdesk.services;

import com.samuelm.helpdesk.domain.Pessoa;
import com.samuelm.helpdesk.domain.Tecnico;
import com.samuelm.helpdesk.dtos.TecnicoDTO;
import com.samuelm.helpdesk.repositories.PessoaRepository;
import com.samuelm.helpdesk.repositories.TecnicoRepository;
import com.samuelm.helpdesk.services.exceptions.DataIntegrityViolationException;
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
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!  Id: " + id));
    }
    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO dto) {
        dto.setId(null);
        validaPorCpfEEmail(dto);
        Tecnico tecnico = new Tecnico(dto);
        return repository.save(tecnico);
    }

    private void validaPorCpfEEmail(TecnicoDTO dto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(dto.getCpf());
        if(obj.isPresent() && obj.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }
        obj = pessoaRepository.findByEmail(dto.getEmail());
        if(obj.isPresent() && obj.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }

    }
}
