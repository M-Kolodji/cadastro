package com.cadastrodepessoa.cadastro.pessoa.service;

import com.cadastrodepessoa.cadastro.api.pessoa.service.PessoaService;
import com.cadastrodepessoa.cadastro.exceptions.BusinessException;
import com.cadastrodepessoa.cadastro.api.pessoa.entity.Pessoa;
import com.cadastrodepessoa.cadastro.api.pessoa.enums.TipoDocumento;
import com.cadastrodepessoa.cadastro.api.pessoa.repository.PessoaRepository;
import com.cadastrodepessoa.cadastro.api.pessoa.service.impl.PessoaServiceImpl;
import com.cadastrodepessoa.cadastro.pessoa.utils.PessoaUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class PessoaServiceTest {

    PessoaService service;

    @MockBean
    PessoaRepository repository;

    @BeforeEach
    void setUp() {
        service = new PessoaServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve criar o registro referente a uma pessoa")
    void salvar() {

        var pessoa = PessoaUtil.getPessoa();
        pessoa.setId(null);

        when(repository.save(any(Pessoa.class))).thenReturn(PessoaUtil.getPessoa());

        Pessoa pessoaSalva = service.salvar(pessoa);

        assertNotNull(pessoaSalva);
        assertNotNull(pessoaSalva.getId());
        assertEquals(pessoaSalva.getNome(), pessoa.getNome());
        assertEquals(pessoaSalva.getDocumento(), pessoa.getDocumento());
        assertEquals(pessoaSalva.getTipoDocumento(), pessoa.getTipoDocumento());

    }

    @Test
    @DisplayName("Deve lançar um erro ao tentar criar o registro referente a uma pessoa com tipo de documento CPF informando CNPJ")
    void salvarPessoaComTipoDocumentoCpfComDadosCnpj() {

        var pessoa = PessoaUtil.getPessoa();
        pessoa.setId(null);
        pessoa.setDocumento("21.235.345/0001-87");

        Throwable throwable = catchThrowable(() -> service.salvar(pessoa));

        assertThat(throwable)
                .isInstanceOf(BusinessException.class)
                .hasMessage("O documento informado não é um CPF");

    }

    @Test
    @DisplayName("Deve lançar um erro ao tentar criar o registro referente a uma pessoa com tipo de documento CNPJ informando CPF")
    void salvarPessoaComTipoDocumentoCnpjComDadosCpf() {

        var pessoa = PessoaUtil.getPessoa();
        pessoa.setId(null);
        pessoa.setTipoDocumento(TipoDocumento.CNPJ);
        pessoa.setDocumento("211.235.345-87");

        Throwable throwable = catchThrowable(() -> service.salvar(pessoa));

        assertThat(throwable)
                .isInstanceOf(BusinessException.class)
                .hasMessage("O documento informado não é um CNPJ");

    }


    @Test
    @DisplayName("Deve lançar um erro ao tentar criar o registro referente a uma pessoa com tipo de documento inválido")
    void salvarPessoaComTipoDocumentoInvalido() {

        var pessoa = PessoaUtil.getPessoa();
        pessoa.setId(null);
        pessoa.setTipoDocumento(null);

        Throwable throwable = catchThrowable(() -> service.salvar(pessoa));

        assertThat(throwable)
                .isInstanceOf(BusinessException.class)
                .hasMessage("Tipo documento inválido. O tipo do documento deve ser CPF ou CNPJ");

    }

}
