package com.caldeira.projetofinal.zelda.services;

import com.caldeira.projetofinal.zelda.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ZeldaGatewayServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ZeldaGatewayService zeldaGatewayService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll_Success() {
        GameListResponseModel.GameDetails gameDetails = new GameListResponseModel.GameDetails("Game 1", "Description", "Dev", "Pub", "2023-01-01", "1");
        GameListResponseModel response = new GameListResponseModel(true, 1, List.of(gameDetails));

        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class)))
                .thenReturn(response);

        List<GameModel> result = zeldaGatewayService.getAll(0, 5);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Game 1", result.get(0).getName());
    }

    @Test
    void testGetAll_UnsuccessfulResponse() {
        GameListResponseModel response = new GameListResponseModel(false, 0, List.of()); // isSuccess = false

        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class)))
                .thenReturn(response);

        List<GameModel> result = zeldaGatewayService.getAll(0, 5);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAll_EmptyResponse() {
        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class)))
                .thenReturn(null);

        List<GameModel> result = zeldaGatewayService.getAll(0, 5);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAll_EmptyDataResponse() {
        GameListResponseModel response = new GameListResponseModel(true, 0, List.of()); // Data é vazio

        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class)))
                .thenReturn(response);

        List<GameModel> result = zeldaGatewayService.getAll(0, 5);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAll_NullRequest() {
        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class)))
                .thenReturn(null);

        List<GameModel> result = zeldaGatewayService.getAll(null, null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAll_NullDataResponse() {
        GameListResponseModel response = new GameListResponseModel(true, 0, null); // Data é null

        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class)))
                .thenReturn(response);

        List<GameModel> result = zeldaGatewayService.getAll(0, 5);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAll_ResponseIsNull() {
        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class)))
                .thenReturn(null);

        List<GameModel> result = zeldaGatewayService.getAll(0, 5);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetById_Success() {
        GameModel gameModel = new GameModel("Game 1", "Description", "Dev", "Pub", "2023-01-01", "1");
        GameResponseModel responseModel = new GameResponseModel(gameModel);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(GameResponseModel.class)))
                .thenReturn(new ResponseEntity<>(responseModel, HttpStatus.OK));

        GameModel result = zeldaGatewayService.getById("1");

        assertNotNull(result);
        assertEquals("Game 1", result.getName());
    }

    @Test
    void testGetById_NotFound() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(GameResponseModel.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        GameModel result = zeldaGatewayService.getById("1");

        assertNull(result);
    }

    @Test
    void testGetById_shouldReturnNullWhenResponseBodyIsNull() {
        ResponseEntity<GameResponseModel> testResponse = new ResponseEntity<>(null, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(GameResponseModel.class)))
                .thenReturn(testResponse);
        GameModel game = zeldaGatewayService.getById("test-id");

        assertNull(game);
    }

    @Test
    void testGetById_shouldReturnNullWhenHttpStatusIsNotOk() {
        GameModel gameModel = new GameModel("Game 1", "Description", "Dev", "Pub", "2023-01-01", "1");
        ResponseEntity<GameResponseModel> mockResponse = new ResponseEntity<>(new GameResponseModel(gameModel), HttpStatus.NOT_FOUND);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(GameResponseModel.class)))
                .thenReturn(mockResponse);

        GameModel game = zeldaGatewayService.getById("test-id");

        assertNull(game);
    }


    @Test
    void testGetAllByName_Success() {
        GameListResponseModel.GameDetails gameDetails = new GameListResponseModel.GameDetails("Game 1", "Description", "Dev", "Pub", "2023-01-01", "1");
        GameListResponseModel response = new GameListResponseModel(true, 1, List.of(gameDetails));

        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class)))
                .thenReturn(response);

        List<GameModel> result = zeldaGatewayService.getAllByName("Game");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Game 1", result.get(0).getName());
    }

    @Test
    void testGetAllByName_UnsuccessfulResponse() {
        GameListResponseModel response = new GameListResponseModel(false, 0, List.of()); // isSuccess é false

        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class)))
                .thenReturn(response);

        List<GameModel> result = zeldaGatewayService.getAllByName("Game");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllByName_NullDataResponse() {
        GameListResponseModel response = new GameListResponseModel(true, 0, null); // Data é null

        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class)))
                .thenReturn(response);

        List<GameModel> result = zeldaGatewayService.getAllByName("Game");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllByName_EmptyResponse() {
        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class))).thenReturn(null);

        List<GameModel> result = zeldaGatewayService.getAllByName("Game");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllByName_ExceptionHandled() {
        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class)))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        List<GameModel> result = zeldaGatewayService.getAllByName("Game");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}


