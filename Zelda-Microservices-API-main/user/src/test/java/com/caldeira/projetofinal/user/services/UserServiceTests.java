package com.caldeira.projetofinal.user.services;

import com.caldeira.projetofinal.user.entities.UserEntity;
import com.caldeira.projetofinal.user.models.request.UserRequestModel;
import com.caldeira.projetofinal.user.models.response.UserResponseModel;
import com.caldeira.projetofinal.user.repositories.UserRepository;
import com.caldeira.projetofinal.user.validators.UserRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserRequestValidator userRequestValidator;
    @InjectMocks
    private UserService userService;
    private UserEntity testUserEntity;
    private UserRequestModel testRequestModel;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        testUserEntity = new UserEntity();
        testUserEntity.setId(UUID.randomUUID());
        testUserEntity.setFirstName("John");
        testUserEntity.setLastName("Doe");
        testUserEntity.setCreationDate(LocalDateTime.now());

        testRequestModel = new UserRequestModel();
        testRequestModel.setFirstName("Jane");
        testRequestModel.setLastName("Smith");
    }

    @Test
    void testGetAll() {
        when(userRepository.findAll()).thenReturn(List.of(testUserEntity));

        List<UserResponseModel> users = userService.getAll();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(testUserEntity.getId(), users.get(0).getId());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetByIdSuccess() {
        when(userRepository.findById(testUserEntity.getId())).thenReturn(Optional.of(testUserEntity));

        UserResponseModel userResponse = userService.getById(testUserEntity.getId());

        assertNotNull(userResponse);
        assertEquals(testUserEntity.getId(), userResponse.getId());
        assertEquals(testUserEntity.getFirstName(), userResponse.getFirstName());
        assertEquals(testUserEntity.getLastName(), userResponse.getLastName());

        verify(userRepository, times(1)).findById(testUserEntity.getId());
    }

    @Test
    void testGetByIdNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        UserResponseModel userResponse = userService.getById(nonExistentId);

        assertNull(userResponse);

        verify(userRepository, times(1)).findById(nonExistentId);
    }

    @Test
    void testCreate() {
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> {
            UserEntity entity = invocation.getArgument(0);
            entity.setId(UUID.randomUUID());
            return entity;
        });

        UserResponseModel createdUser = userService.create(testRequestModel);

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals(testRequestModel.getFirstName(), createdUser.getFirstName());
        assertEquals(testRequestModel.getLastName(), createdUser.getLastName());

        verify(userRequestValidator, times(1)).validate(testRequestModel);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testUpdateSuccess() {
        when(userRepository.existsById(testUserEntity.getId())).thenReturn(true);
        when(userRepository.getReferenceById(testUserEntity.getId())).thenReturn(testUserEntity);
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserRequestModel updateRequest = new UserRequestModel();
        updateRequest.setFirstName("Updated");
        updateRequest.setLastName("Name");

        UserResponseModel updatedUser = userService.update(testUserEntity.getId(), updateRequest);

        assertNotNull(updatedUser);
        assertEquals(testUserEntity.getId(), updatedUser.getId());
        assertEquals("Updated", updatedUser.getFirstName());
        assertEquals("Name", updatedUser.getLastName());

        verify(userRequestValidator, times(1)).validate(updateRequest);
        verify(userRepository, times(1)).existsById(testUserEntity.getId());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testUpdateNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(userRepository.existsById(nonExistentId)).thenReturn(false);

        UserResponseModel updatedUser = userService.update(nonExistentId, testRequestModel);

        assertNull(updatedUser);

        verify(userRequestValidator, times(1)).validate(testRequestModel);
        verify(userRepository, times(1)).existsById(nonExistentId);
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void testDeleteByIdSuccess() {
        when(userRepository.existsById(testUserEntity.getId())).thenReturn(true);

        boolean result = userService.deleteById(testUserEntity.getId());

        assertTrue(result);

        verify(userRepository, times(1)).existsById(testUserEntity.getId());
        verify(userRepository, times(1)).deleteById(testUserEntity.getId());
    }

    @Test
    void testDeleteByIdNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(userRepository.existsById(nonExistentId)).thenReturn(false);

        boolean result = userService.deleteById(nonExistentId);

        assertFalse(result);

        verify(userRepository, times(1)).existsById(nonExistentId);
        verify(userRepository, never()).deleteById(nonExistentId);
    }
}
