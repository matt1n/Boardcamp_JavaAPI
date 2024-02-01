package com.boardcamp.api.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RentalDTO {

    @NotBlank
    private Long customerId;

    @NotBlank
    private Long gameId;

    @NotBlank
    private LocalDate rentDate;

    @NotBlank
    private Long daysRented;

    private LocalDate returnDate;

    @NotBlank
    private Long originalPrice;

    @NotBlank
    private Long delayFee;
}
