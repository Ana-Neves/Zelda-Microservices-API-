package com.caldeira.projetofinal.zelda.controllers;

import com.caldeira.projetofinal.zelda.models.GameModel;
import com.caldeira.projetofinal.zelda.services.ZeldaGatewayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ZeldaControllerTests {
    @Mock
    private ZeldaGatewayService zeldaGatewayService;

    @InjectMocks
    private ZeldaController zeldaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        List<GameModel> zeldaGameList = List.of(
                new GameModel("The Legend of Zelda", "Description1001", "Nintendo R&D 4", "Nintendo", "February 21, 1986", "1"),
                new GameModel("The Legend of Zelda: A Link to the Past", "Description1234", "Nintendo", "Nintendo", "April 13, 1992", "2")
        );
        when(zeldaGatewayService.getAll(0, 5)).thenReturn(zeldaGameList);

        ResponseEntity<List<GameModel>> response = zeldaController.getAll(0, 5);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("The Legend of Zelda", response.getBody().getFirst().getName());
        verify(zeldaGatewayService, times(1)).getAll(0, 5);
    }

    @Test
    void testGetByIdFound() {
        GameModel zeldaGame = new GameModel("The Legend of Zelda", "Description1001", "Nintendo R&D 4", "Nintendo", "February 21, 1986", "1");
        when(zeldaGatewayService.getById("1")).thenReturn(zeldaGame);

        ResponseEntity<GameModel> response = zeldaController.getById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("The Legend of Zelda", response.getBody().getName());
        verify(zeldaGatewayService, times(1)).getById("1");
    }

    @Test
    void testGetByIdNotFound() {
        when(zeldaGatewayService.getById("999")).thenReturn(null);

        ResponseEntity<GameModel> response = zeldaController.getById("999");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(zeldaGatewayService, times(1)).getById("999");
    }

    @Test
    void testGetAllByName() {
        List<GameModel> zeldaGameList = List.of(
                new GameModel("The Legend of Zelda", "Description1001", "Nintendo R&D 4", "Nintendo", "February 21, 1986", "1")
        );
        when(zeldaGatewayService.getAllByName("The Legend of Zelda")).thenReturn(zeldaGameList);

        ResponseEntity<List<GameModel>> response = zeldaController.getAllByName("The Legend of Zelda");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("The Legend of Zelda", response.getBody().getFirst().getName());
        verify(zeldaGatewayService, times(1)).getAllByName("The Legend of Zelda");
    }

    @Test
    void testGetAllByNameNotFound() {
        when(zeldaGatewayService.getAllByName("NonExistingGame")).thenReturn(List.of());

        ResponseEntity<List<GameModel>> response = zeldaController.getAllByName("NonExistingGame");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
        verify(zeldaGatewayService, times(1)).getAllByName("NonExistingGame");
    }
}
