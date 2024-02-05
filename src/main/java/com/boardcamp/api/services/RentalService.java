package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.exceptions.CustomerNotFoundException;
import com.boardcamp.api.exceptions.GameNotFoundException;
import com.boardcamp.api.exceptions.RentalUnprocessableEntityException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.RentalRepository;

@Service
public class RentalService {
    final RentalRepository rentalRepository;
    final CustomerService customerService;
    final GameService gameService;

    public RentalService(RentalRepository rentalRepository, CustomerService customerService, GameService gameService){
        this.rentalRepository = rentalRepository;
        this.customerService = customerService;
        this.gameService = gameService;
    }

    public List<RentalModel> findAll(){
        return rentalRepository.findAll();
    }

    public RentalModel save(RentalDTO dto){

        if (!customerService.customerRepository.existsById(dto.getCustomerId())) {
            throw new CustomerNotFoundException("Usuario não cadastrado");
        }

        if (!gameService.gameRepository.existsById(dto.getGameId())){
            throw new GameNotFoundException("Jogo não encontrado");
        }

        List<RentalModel> rentals = rentalRepository.findByGameId(dto.getGameId());


        CustomerModel customer = customerService.findById(dto.getCustomerId());

        GameModel game = gameService.findById(dto.getGameId());

        if (rentals.size()>=game.getStockTotal()) {
            throw new RentalUnprocessableEntityException("Todos exemplares desse jogo já estão alugados");
        }

        RentalModel rental = new RentalModel(dto,customer,game);
        return rentalRepository.save(rental);
    }
}
