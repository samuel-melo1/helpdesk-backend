package com.samuelm.helpdesk.repositories;

import com.samuelm.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
