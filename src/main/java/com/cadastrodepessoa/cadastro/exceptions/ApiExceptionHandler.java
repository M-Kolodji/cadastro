package com.cadastrodepessoa.cadastro.exceptions;

import com.cadastrodepessoa.cadastro.api.pessoa.dto.ResponseDTO;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseDTO<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> erros = e.getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + " - "+ erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseDTO<>(erros);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseDTO<String> businessException(BusinessException e) {
        return new ResponseDTO<>(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseDTO<String> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseDTO<>(e.getRootCause().getLocalizedMessage());
    }

}
