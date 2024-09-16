package com.samuelm.helpdesk.resources;

import com.samuelm.helpdesk.dtos.ChamadoDTO;
import com.samuelm.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;
    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable("id") Integer id){
        return  ResponseEntity.ok().body(new ChamadoDTO(service.findById(id)));
    }
    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll().stream().map(ChamadoDTO::new).collect(Collectors.toList()));
    }
}
