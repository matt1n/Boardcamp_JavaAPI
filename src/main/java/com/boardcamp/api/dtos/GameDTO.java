package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GameDTO {

    @NotBlank
    private String name;
    
    @NotBlank
    private String image;

    @Positive
    private Long stockTotal;

    @Positive
    private Long pricePerDay;

}
