package com.cadastrodepessoa.cadastro.pessoa.controller;

import com.cadastrodepessoa.cadastro.api.pessoa.controller.PessoaController;
import com.cadastrodepessoa.cadastro.api.pessoa.dto.PessoaDTO;
import com.cadastrodepessoa.cadastro.api.pessoa.entity.Pessoa;
import com.cadastrodepessoa.cadastro.api.pessoa.service.PessoaService;
import com.cadastrodepessoa.cadastro.pessoa.utils.PessoaUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@WebMvcTest(PessoaController.class)
class PessoaControllerTest {

    static final String API_PESSOA = "/pessoa";

    @Autowired
    MockMvc mvc;

    @InjectMocks
    ObjectMapper objectMapper;

    @MockBean
    PessoaService service;

    @Test
    @DisplayName("Deve criar o registro referente a uma pessoa")
    void salvar() throws Exception {

        Mockito.when(service.salvar(Mockito.any(Pessoa.class))).thenReturn(PessoaUtil.getPessoa());

        String json = objectMapper.writeValueAsString(PessoaUtil.getPessoaDTO());

        RequestBuilder request = MockMvcRequestBuilders
                .post(API_PESSOA)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.erros").isEmpty())
                .andExpect(jsonPath("$.status").value(true));

    }

    @Test
    @DisplayName("Deve lançar um erro ao tentar criar o registro inválido referente a uma pessoa")
    void salvarPessoaInvalida() throws Exception {

        String json = objectMapper.writeValueAsString(new PessoaDTO());

        RequestBuilder request = MockMvcRequestBuilders
                .post(API_PESSOA)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.erros").isNotEmpty())
                .andExpect(jsonPath("$.status").value(false));

    }

}
