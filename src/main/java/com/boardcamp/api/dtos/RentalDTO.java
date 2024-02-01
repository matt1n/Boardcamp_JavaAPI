package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RentalDTO {

    @NotBlank
    private Long customerId;

    @NotBlank
    private Long gameId;

    @NotBlank
    private String rentDate;

    @NotBlank
    private Long daysRented;

    @NotBlank
    private Long returnDate;

    @NotBlank
    private Long originalPrice;

    @NotBlank
    private Long delayFee;
}
