package com.boardcamp.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.exceptions.GameConflictException;
import com.boardcamp.api.exceptions.GameNotFoundException;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;

@Service
public class GameService {
    final GameRepository gameRepository;

    GameService( GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameModel> findAll(){
        return gameRepository.findAll();
    }

    public GameModel findById(Long id){
        return gameRepository.findById(id).orElseThrow(
            () -> new GameNotFoundException("Jogo não existe")
        );
    }

    public GameModel save(GameDTO dto) {
       if (gameRepository.existsByName(dto.getName())){
        throw new GameConflictException("Esse jogo já está registrado");
       }

       


        GameModel game = new GameModel(dto);
        return gameRepository.save(game);
    }

}
