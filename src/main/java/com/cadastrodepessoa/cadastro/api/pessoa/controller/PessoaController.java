package com.cadastrodepessoa.cadastro.api.pessoa.controller;

import com.cadastrodepessoa.cadastro.api.pessoa.dto.PessoaDTO;
import com.cadastrodepessoa.cadastro.api.pessoa.dto.ResponseDTO;
import com.cadastrodepessoa.cadastro.api.pessoa.entity.Pessoa;
import com.cadastrodepessoa.cadastro.api.pessoa.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/pessoa")
@RequiredArgsConstructor
@Api(tags = "Pessoa")
public class PessoaController {

    private final PessoaService service;
    private final ModelMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation(value = "Salvar Pessoa", response = PessoaDTO.class)
    public ResponseDTO<PessoaDTO> salvar(@RequestBody @Valid PessoaDTO dto) {
        Pessoa pessoa = service.salvar(mapper.map(dto, Pessoa.class));
        return new ResponseDTO<>(mapper.map(pessoa, PessoaDTO.class));
    }

}
