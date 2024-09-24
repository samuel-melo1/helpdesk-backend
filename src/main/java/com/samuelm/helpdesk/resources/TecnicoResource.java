package com.samuelm.helpdesk.resources;

import com.samuelm.helpdesk.domain.Tecnico;
import com.samuelm.helpdesk.dtos.TecnicoDTO;
import com.samuelm.helpdesk.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO dto){
        Tecnico newTec = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTec.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody TecnicoDTO dto){
        Tecnico oldObj = service.update(id,  dto);
        return ResponseEntity.ok().body(new TecnicoDTO(oldObj));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
