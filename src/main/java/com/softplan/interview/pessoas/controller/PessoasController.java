package com.softplan.interview.pessoas.controller;

import com.softplan.interview.pessoas.model.NegocioResponse;
import com.softplan.interview.pessoas.model.Pessoa;
import com.softplan.interview.pessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.*;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.Optional;

@RestController
public class PessoasController {
    @Autowired
    PessoaRepository repository;

    @GetMapping("/pessoas")
    public Iterable<Pessoa> get(@PathParam("cpf") String cpf) {
        if(cpf == null) {
            return repository.findAll();
        }
        return repository.findByCpf(cpf);
    }

    @PutMapping("/pessoas")
    public Pessoa put(@Valid @RequestBody Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> post(@Valid @RequestBody Pessoa pessoa) {
        Iterable<Pessoa> pessoas = repository.findByCpf(pessoa.getCpf());
        if(!((Collection<?>)pessoas).isEmpty()) {
            return new ResponseEntity(new NegocioResponse(HttpStatus.BAD_REQUEST, "CPF já cadastrado!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(repository.save(pessoa), HttpStatus.CREATED);
    }

    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> delete(@PathVariable Long id) {
        Optional<Pessoa> pessoa = repository.findById(id);
        if(!pessoa.isPresent()) {
            return new ResponseEntity(new NegocioResponse(HttpStatus.NOT_FOUND, "Pessoa não encontrada!"), HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}