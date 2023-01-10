package com.cadastrodepessoa.cadastro.pessoa.utils;

import com.cadastrodepessoa.cadastro.api.pessoa.dto.PessoaDTO;
import com.cadastrodepessoa.cadastro.api.pessoa.entity.Pessoa;
import com.cadastrodepessoa.cadastro.api.pessoa.enums.TipoDocumento;

import java.util.UUID;

public abstract class PessoaUtil {

    public static Pessoa getPessoa() {
        return Pessoa
                .builder()
                .id(UUID.randomUUID())
                .nome("Teste")
                .tipoDocumento(TipoDocumento.CPF)
                .documento("222.222.222-22")
                .build();
    }

    public static PessoaDTO getPessoaDTO() {
        return PessoaDTO
                .builder()
                .nome("Teste")
                .tipoDocumento(TipoDocumento.CPF)
                .documento("222.222.222-22")
                .build();
    }

}
