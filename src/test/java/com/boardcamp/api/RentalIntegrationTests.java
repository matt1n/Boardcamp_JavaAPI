package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

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
import com.boardcamp.api.models.RentalModel;
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
        RentalDTO dto = new RentalDTO(9L, 1L, 3L);  

        HttpEntity<RentalDTO> body = new HttpEntity<>(dto);

        ResponseEntity<String> response = restTemplate.exchange("/rentals", HttpMethod.POST, body, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(0, rentalRepository.count());
    }

    @Test
    void givenValidRental_whenCreatingRental_thenCreatesRental(){
        CustomerModel customer  = new CustomerModel(null, "nullicio", "12121212121");
        CustomerModel customerCreated = customerRepository.save(customer);

        GameModel game = new GameModel(null, "jogo da vuaida", "aaaaaa", 3L, 1500L);
        GameModel gameCreated = gameRepository.save(game);

        RentalDTO dto = new RentalDTO(customerCreated.getId(), gameCreated.getId(), 3L);




        HttpEntity<RentalDTO> body = new HttpEntity<>(dto);

        ResponseEntity<String> response = restTemplate.exchange("/rentals", HttpMethod.POST, body, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, rentalRepository.count());
    }

    @Test
    void givenValidRentalWithoutFee_whenUpdatingRental_thenUpdatesRental(){
        CustomerModel customer  = new CustomerModel(null, "nullicio", "12121212121");
        CustomerModel customerCreated = customerRepository.save(customer);

        GameModel game = new GameModel(null, "jogo da vuaida", "aaaaaa", 3L, 1500L);
        GameModel gameCreated = gameRepository.save(game);

        RentalDTO dto = new RentalDTO(customerCreated.getId(), gameCreated.getId(), 3L);
        RentalModel rental = new RentalModel(dto, customerCreated, gameCreated);
        RentalModel rentalCreated = rentalRepository.save(rental);

        ResponseEntity<String> response = restTemplate.exchange("/rentals/{id}/return", HttpMethod.PUT, null, String.class, rentalCreated.getId());

        Optional<RentalModel> rentalSaved = rentalRepository.findById(rentalCreated.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, rentalRepository.count());
        assertEquals(rentalSaved.get().getDelayFee(), 0);
    }
}
