package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.exceptions.CustomerNotFoundException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.RentalRepository;
import com.boardcamp.api.services.CustomerService;
import com.boardcamp.api.services.GameService;
import com.boardcamp.api.services.RentalService;

@SpringBootTest
public class RentalUnitTests {
    
    @InjectMocks
    private RentalService rentalService;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private GameService gameService;

    @Test
    void givenInvalidCustomer_whenCreatingRental_thenThrowsError() {
        
        //given
        
        RentalDTO dto = new RentalDTO(1L, 1L, 3L);
        doReturn(false).when(customerService).existsById(dto.getCustomerId());

        //when

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> rentalService.save(dto));

        //then

        verify(customerService, times(1)).existsById(any());
        verify(rentalRepository, times(0)).save(any());
        assertNotNull(exception);
        assertEquals("Usuario não cadastrado", exception.getMessage());
    }

    @Test
    void givenValidRental_whenCreatingRental_thenCreatesRental(){
        
        RentalDTO dto = new RentalDTO(1L, 1L, 3L);
        CustomerModel customer = new CustomerModel(null,"sim","11111111111");
        GameModel game = new GameModel(null, "jenga","aaaaaaaa",3L,1500L);
        RentalModel rental = new RentalModel(dto, customer, game);
        
        doReturn(true).when(customerService).existsById(any());
        doReturn(true).when(gameService).existsById(any());
        doReturn(List.of(rental)).when(rentalRepository).findByGameId(any());
        doReturn(customer).when(customerService).findById(any());
        doReturn(game).when(gameService).findById(any());
        doReturn(rental).when(rentalRepository).save(any());


        RentalModel result = rentalService.save(dto);


        assertNotNull(result);
        verify(customerService, times(1)).existsById(any());
        verify(gameService, times(1)).existsById(any());
        verify(rentalRepository, times(1)).findByGameId(any());
        verify(customerService, times(1)).findById(any());
        verify(gameService, times(1)).findById(any());
        verify(rentalRepository, times(1)).save(any());
        assertEquals(rental, result);
    }

    @Test
    void givenValidRentalWithoutFee_whenUpdatingRental_thenUpdatesRental(){
        RentalDTO dto = new RentalDTO(1L, 1L, 3L);
        CustomerModel customer = new CustomerModel(1L, "aaaaa", "11111111111");
        GameModel game = new GameModel(1L, "Jogo da vida", "aaaa", 3L, 1500L);
        RentalModel rental = new RentalModel(dto, customer, game);
        RentalModel newRental = new RentalModel(1L, rental.getRentDate(), rental.getDaysRented(), null, rental.getOriginalPrice(), rental.getDelayFee(), customer, game);
        newRental.setReturnDate(LocalDate.now());

        doReturn(Optional.of(rental)).when(rentalRepository).findById(any());
        doReturn(newRental).when(rentalRepository).save(any());

        RentalModel result = rentalService.update(rental.getId());

        assertNotNull(result);
        verify(rentalRepository, times(1)).findById(any());
        verify(rentalRepository, times(1)).save(any());
        assertEquals(newRental, result);
    }
}
