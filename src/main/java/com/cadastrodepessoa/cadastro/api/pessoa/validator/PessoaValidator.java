package com.cadastrodepessoa.cadastro.api.pessoa.validator;

import com.cadastrodepessoa.cadastro.api.pessoa.enums.TipoDocumento;
import com.cadastrodepessoa.cadastro.exceptions.BusinessException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.Normalizer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class PessoaValidator {

    public static void validaDocuemnto(TipoDocumento tipoDocumento, String documento) {

        validaTipoDocumento(tipoDocumento);

        var documentoSanitizado = sanitizaDocumento(documento);

        if(documentoSanitizado.length() == 11 && !tipoDocumento.equals(TipoDocumento.CPF)) {
            throw new BusinessException("O documento informado não é um CNPJ");
        } else if(documentoSanitizado.length() == 14 && !tipoDocumento.equals(TipoDocumento.CNPJ)) {
            throw new BusinessException("O documento informado não é um CPF");
        }

    }

    private static void validaTipoDocumento(TipoDocumento tipoDocumento) {
        if(tipoDocumento == null ||
                !tipoDocumento.equals(TipoDocumento.CPF) && !tipoDocumento.equals(TipoDocumento.CNPJ)) {
            throw new BusinessException("Tipo documento inválido. O tipo do documento deve ser CPF ou CNPJ");
        }
    }

    private static String sanitizaDocumento(String documento) {
        return Normalizer.normalize(documento, Normalizer.Form.NFD).replaceAll("[^a-zA-Z0-9]", "");
    }

}
