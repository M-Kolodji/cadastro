package com.cadastrodepessoa.cadastro.api.pessoa.repository;

import com.cadastrodepessoa.cadastro.api.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
}
