package com.softplan.interview.pessoas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "O campo nome não pode estar vazio")
    private String nome;

    private String sexo;

    @Email(message = "O campo email está fora do padrão exemplo@dominio.com")
    private String email;

    @NotNull(message = "O campo nascimento deve ser preenchido")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate nascimento;
    private String naturalidade;
    private String nacionalidade;

    @NotNull(message = "O campo cpf deve ser preenchido")
    @Column(unique = true)
    @CPF(message = "CPF inválido")
    @Length(min = 11, max = 11)
    private String cpf;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime criacao;

    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime atualizacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getCriacao() {
        return criacao;
    }

    public LocalDateTime getAtualizacao() {
        return atualizacao;
    }

    public Long getId() {
        return id;
    }
}