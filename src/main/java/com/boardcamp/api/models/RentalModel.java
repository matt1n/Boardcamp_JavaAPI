package com.boardcamp.api.models;

import java.time.LocalDate;

import com.boardcamp.api.dtos.RentalDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class RentalModel {

    public RentalModel(RentalDTO dto, CustomerModel customer, GameModel game) {
        this.rentDate = LocalDate.now();
        this.daysRented = dto.getDaysRented();
        this.returnDate = dto.getReturnDate();
        this.originalPrice = dto.getOriginalPrice();
        this.delayFee = dto.getDelayFee();
        this.customer = customer;
        this.game = game;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private LocalDate rentDate;

    @Column(nullable = false)
    private Long daysRented;

    @Column(nullable = true)
    private LocalDate returnDate;
    
    @Column(nullable = false)
    private Long originalPrice;

    @Column(nullable = false)
    private Long delayFee;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "gameId", nullable = false)
    private GameModel game;
    
}