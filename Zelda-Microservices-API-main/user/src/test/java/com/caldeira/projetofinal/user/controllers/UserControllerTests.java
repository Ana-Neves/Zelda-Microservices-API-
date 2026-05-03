package com.caldeira.projetofinal.user.controllers;

import com.caldeira.projetofinal.user.models.request.UserRequestModel;
import com.caldeira.projetofinal.user.models.response.UserResponseModel;
import com.caldeira.projetofinal.user.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsersSuccess() {
        List<UserResponseModel> mockUser = List.of(
                new UserResponseModel(UUID.randomUUID(), "Carolina", "Oliveira", LocalDateTime.now()),
                new UserResponseModel(UUID.randomUUID(), "Carina", "Oliveira", LocalDateTime.now())
        );

        when(userService.getAll()).thenReturn(mockUser);

        ResponseEntity<List<UserResponseModel>> response = userController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(userService, times(1)).getAll();
    }

    @Test
    void testGetAllIsNull() {
        when(userService.getAll()).thenReturn(null);

        ResponseEntity<List<UserResponseModel>> response = userController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response);
        assertNull(response.getBody());
        verify(userService, times(1)).getAll();
    }

    @Test
    void testGetByIdSuccess() {
        UUID userId = UUID.randomUUID();
        UserResponseModel mockUser = new UserResponseModel(userId, "Éverson", "Boeno", LocalDateTime.now());

        when(userService.getById(userId)).thenReturn(mockUser);

        ResponseEntity<UserResponseModel> response = userController.getById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertThat(response.getBody()).isEqualTo(mockUser);
        verify(userService, times(1)).getById(userId);
    }

    @Test
    void testGetByIdNotFound() {
        UUID userId = UUID.randomUUID();
        when(userService.getById(userId)).thenReturn(null);

        ResponseEntity<UserResponseModel> response = userController.getById(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService, times(1)).getById(userId);
    }

    @Test
    void testCreate() {
        UserRequestModel mockRequest = new UserRequestModel("Lívia", "Rosa");
        UserResponseModel mockUser = new UserResponseModel(UUID.randomUUID(), "Thaís", "Rosa", LocalDateTime.now());

        when(userService.create(mockRequest)).thenReturn(mockUser);

        ResponseEntity<UserResponseModel> response = userController.create(mockRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockUser.getId(), response.getBody().getId());
        verify(userService, times(1)).create(mockRequest);
    }

    @Test
    void testUpdateSuccess() {
        UUID userId = UUID.randomUUID();
        final String updatedFirstName = "New first name";
        final String updatedLastName = "New last name";

        UserRequestModel updateRequest = new UserRequestModel(updatedFirstName, updatedLastName);
        UserResponseModel updateUser = UserResponseModel.builder().id(userId).firstName(updatedFirstName).lastName(updatedLastName).creationDate(LocalDateTime.now()).build();

        when(userService.update(userId, updateRequest)).thenReturn(updateUser);

        ResponseEntity<UserResponseModel> response = userController.update(userId, updateRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedFirstName, response.getBody().getFirstName());
        assertEquals(updatedLastName, response.getBody().getLastName());
        verify(userService, times(1)).update(userId, updateRequest);
    }

    @Test
    void testUpdateNotFound() {
        UUID userId = UUID.randomUUID();
        final String updatedFirstName = "New first name";
        final String updatedLastName = "New last name";

        UserRequestModel updateRequest = new UserRequestModel(updatedFirstName, updatedLastName);

        when(userService.update(userId, updateRequest)).thenReturn(null);

        ResponseEntity<UserResponseModel> response = userController.update(userId, updateRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService, times(1)).update(userId, updateRequest);
    }

    @Test
    void testDeleteByIdSuccess() {
        UUID userId = UUID.randomUUID();
        UserResponseModel mockUser = new UserResponseModel(userId, "John", "Routledge", LocalDateTime.now());

        when(userService.getById(userId)).thenReturn(mockUser);

        when(userService.deleteById(userId)).thenReturn(true);

        ResponseEntity<Void> response = userController.deleteById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteByIdNotFound() {
        UUID userId = UUID.randomUUID();

        when(userService.getById(userId)).thenReturn(null);

        ResponseEntity<Void> response = userController.deleteById(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService, times(1)).deleteById(userId);
    }
}
