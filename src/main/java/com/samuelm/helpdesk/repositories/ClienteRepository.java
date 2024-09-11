package com.samuelm.helpdesk.repositories;

import com.samuelm.helpdesk.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
