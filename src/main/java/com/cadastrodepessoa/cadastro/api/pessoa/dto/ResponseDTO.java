package com.cadastrodepessoa.cadastro.api.pessoa.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseDTO<T> {

    private T data;
    private List<String> erros;
    private boolean status;

    public ResponseDTO(T data) {
        this.data = data;
        this.status = true;
    }

    public ResponseDTO(String erro) {
        this.status = false;
        this.erros = new ArrayList<>();
        this.erros.add(erro);
    }

    public ResponseDTO(List<String> erros) {
        this.status = false;
        this.erros = erros;
    }

}
