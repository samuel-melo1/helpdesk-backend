package com.samuelm.helpdesk.resources;

import com.samuelm.helpdesk.domain.Tecnico;
import com.samuelm.helpdesk.dtos.TecnicoDTO;
import com.samuelm.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok().body(service.findAll().stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList()));
    }
}
