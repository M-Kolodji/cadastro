package com.cadastrodepessoa.cadastro.api.pessoa.dto;

import com.cadastrodepessoa.cadastro.api.pessoa.enums.TipoDocumento;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private UUID id;

    @NotBlank
    private String nome;

    @NotBlank
    private String documento;

    @NotNull
    private TipoDocumento tipoDocumento;

}
