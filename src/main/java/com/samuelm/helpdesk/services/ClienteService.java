package com.samuelm.helpdesk.services;

import com.samuelm.helpdesk.domain.Pessoa;
import com.samuelm.helpdesk.domain.Cliente;
import com.samuelm.helpdesk.dtos.ClienteDTO;
import com.samuelm.helpdesk.repositories.ClienteRepository;
import com.samuelm.helpdesk.repositories.PessoaRepository;
import com.samuelm.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.samuelm.helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!  Id: " + id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO dto) {
        dto.setId(null);
        validaPorCpfEEmail(dto);
        Cliente tecnico = new Cliente(dto);
        return repository.save(tecnico);
    }

    public Cliente update(Integer id, @Valid ClienteDTO dto) {
        dto.setId(id);
        findById(id);
        validaPorCpfEEmail(dto);
        Cliente oldObj = new Cliente(dto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(ClienteDTO dto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(dto.getCpf());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }
        obj = pessoaRepository.findByEmail(dto.getEmail());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }



}
