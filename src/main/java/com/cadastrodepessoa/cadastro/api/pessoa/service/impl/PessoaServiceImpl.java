package com.cadastrodepessoa.cadastro.api.pessoa.service.impl;

import com.cadastrodepessoa.cadastro.api.pessoa.service.PessoaService;
import com.cadastrodepessoa.cadastro.api.pessoa.entity.Pessoa;
import com.cadastrodepessoa.cadastro.api.pessoa.repository.PessoaRepository;
import com.cadastrodepessoa.cadastro.api.pessoa.validator.PessoaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repository;

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        PessoaValidator.validaDocuemnto(pessoa.getTipoDocumento(), pessoa.getDocumento());
        return repository.save(pessoa);
    }

}
