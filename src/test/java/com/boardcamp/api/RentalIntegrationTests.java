package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.repositories.RentalRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentalIntegrationTests {
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GameRepository gameRepository;

    @AfterEach
    public void cleanUpDatabase(){
        rentalRepository.deleteAll();
        customerRepository.deleteAll();
        gameRepository.deleteAll();
    }

    @Test
    void givenInvalidCustomer_whenCreatingRental_thenThrowsError() {
        GameModel game = new GameModel(1L, "aaaa", "aaaqaaaa", 3L, 1500L);
        RentalDTO dto = new RentalDTO(9L, 1L, 3L);  

        HttpEntity<RentalDTO> body = new HttpEntity<>(dto);

        ResponseEntity<String> response = restTemplate.exchange("/rentals", HttpMethod.POST, body, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(0, rentalRepository.count());
    }
}
