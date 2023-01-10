package com.cadastrodepessoa.cadastro.api.pessoa.entity;

import com.cadastrodepessoa.cadastro.api.pessoa.enums.TipoDocumento;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @NotBlank(message = "O campo documento é obrigatório")
    private String documento;

    @NotNull(message = "O campo tipo do documento é obrigatório")
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

}
