package com.samuelm.helpdesk.resources;

import com.samuelm.helpdesk.domain.Chamado;
import com.samuelm.helpdesk.dtos.ChamadoDTO;
import com.samuelm.helpdesk.services.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(new ChamadoDTO(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(ChamadoDTO::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO dto) {
        Chamado obj = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
