package com.samuelm.helpdesk.services;

import com.samuelm.helpdesk.domain.Pessoa;
import com.samuelm.helpdesk.domain.Tecnico;
import com.samuelm.helpdesk.dtos.TecnicoDTO;
import com.samuelm.helpdesk.repositories.PessoaRepository;
import com.samuelm.helpdesk.repositories.TecnicoRepository;
import com.samuelm.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.samuelm.helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado!  Id: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO dto) {
        dto.setId(null);
        dto.setSenha(encoder.encode(dto.getSenha()));
        validaPorCpfEEmail(dto);
        Tecnico tecnico = new Tecnico(dto);
        return repository.save(tecnico);
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO dto) {
        dto.setId(id);
        Tecnico oldObj = findById(id);

        if (!dto.getSenha().equals(oldObj.getSenha())) {
            dto.setSenha(encoder.encode(dto.getSenha()));
        }
        validaPorCpfEEmail(dto);
        Tecnico newObj = new Tecnico(dto);
        return repository.save(newObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(TecnicoDTO dto) {
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
