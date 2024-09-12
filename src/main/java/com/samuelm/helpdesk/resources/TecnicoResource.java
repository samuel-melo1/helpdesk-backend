package com.samuelm.helpdesk.resources;

import com.samuelm.helpdesk.domain.Tecnico;
import com.samuelm.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;
    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }
}
