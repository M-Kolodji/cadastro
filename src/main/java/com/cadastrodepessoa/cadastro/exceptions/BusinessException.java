package com.cadastrodepessoa.cadastro.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String mensagem) {
        super(mensagem);
    }

}
