package com.softplan.interview.pessoas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceController {
    @GetMapping("/source")
    public String getSource() {
        return "https://github.com/TeddyTeddinson/CadastroPessoasInterview";
    }
}