package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 11, message = "CPF tem 11 caracteres")
    private String cpf;
}
