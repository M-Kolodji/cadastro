package com.cadastrodepessoa.cadastro.pessoa.repository;

import com.cadastrodepessoa.cadastro.api.pessoa.repository.PessoaRepository;
import com.cadastrodepessoa.cadastro.api.pessoa.entity.Pessoa;
import com.cadastrodepessoa.cadastro.pessoa.utils.PessoaUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
class PessoaRepositoryTest {

    @Autowired
    PessoaRepository repository;

    @Test
    @DisplayName("Deve criar o registro referente a uma pessoa")
    void salvar() {

        var pessoa = PessoaUtil.getPessoa();
        pessoa.setId(null);

        Pessoa pessoaSalva = repository.save(pessoa);

        assertNotNull(pessoaSalva);
        assertNotNull(pessoaSalva.getId());
        assertEquals(pessoaSalva.getNome(), pessoa.getNome());
        assertEquals(pessoaSalva.getDocumento(), pessoa.getDocumento());
        assertEquals(pessoaSalva.getTipoDocumento(), pessoa.getTipoDocumento());

    }

    @Test
    @DisplayName("Deve lançar um erro ao tentar criar o registro inválido referente a uma pessoa")
    void salvarPessoaInvalida() {

        Throwable throwable = catchThrowable(() -> repository.save(null));

        assertThat(throwable)
                .isInstanceOf(InvalidDataAccessApiUsageException.class)
                .hasMessage("Entity must not be null.; nested exception is java.lang.IllegalArgumentException: Entity must not be null.");

    }

}
