package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.RentalDTO;
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

        CustomerModel customer = customerService.findById(dto.getCustomerId());
        GameModel game = gameService.findById(dto.getGameId());

        RentalModel rental = new RentalModel(dto,customer,game);
        return rentalRepository.save(rental);
    }
}
