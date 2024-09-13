package com.samuelm.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.samuelm.helpdesk.domain.enums.Perfil;
import com.samuelm.helpdesk.dtos.TecnicoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Tecnico extends Pessoa{

    @OneToMany(mappedBy = "tecnico")
    @JsonIgnore
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.CLIENTE);
    }
    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public Tecnico(TecnicoDTO obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
        addPerfil(Perfil.CLIENTE);
    }
    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
