package com.softplan.interview.pessoas.repository;

import com.softplan.interview.pessoas.model.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

    Iterable<Pessoa> findByCpf(String cpf);
}