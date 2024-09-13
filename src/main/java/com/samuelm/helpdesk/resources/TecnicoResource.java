package com.samuelm.helpdesk.resources;

import com.samuelm.helpdesk.domain.Tecnico;
import com.samuelm.helpdesk.dtos.TecnicoDTO;
import com.samuelm.helpdesk.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;
    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(new TecnicoDTO(service.findById(id)));
    }
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll().stream().map(TecnicoDTO::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO dto){
        Tecnico newTec = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTec.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
