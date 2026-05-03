package com.caldeira.projetofinal.user.services;

import com.caldeira.projetofinal.user.entities.UserEntity;
import com.caldeira.projetofinal.user.models.request.UserRequestModel;
import com.caldeira.projetofinal.user.models.response.UserResponseModel;
import com.caldeira.projetofinal.user.repositories.UserRepository;
import com.caldeira.projetofinal.user.validators.UserRequestValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRequestValidator userRequestValidator;

    public UserService (UserRepository userRepository, UserRequestValidator userRequestValidator){
        this.userRepository = userRepository;
        this.userRequestValidator = userRequestValidator;
    }

    public List <UserResponseModel> getAll(){
        return userRepository.findAll().stream().map(
                userEntity -> UserResponseModel.builder()
                        .id(userEntity.getId())
                        .firstName(userEntity.getFirstName())
                        .lastName(userEntity.getLastName())
                        .creationDate(userEntity.getCreationDate()).build()
        ).toList();
    }

    public UserResponseModel getById (UUID id){
        var response = userRepository.findById(id).orElse(null);

        if(response != null){

            UserResponseModel user = new UserResponseModel();

            user.setId(response.getId());
            user.setFirstName(response.getFirstName());
            user.setLastName(response.getLastName());
            user.setCreationDate(response.getCreationDate());

            return user;

        } else {
            return null;
        }
    }

    public UserResponseModel create (UserRequestModel requestModel){

        userRequestValidator.validate(requestModel);

        UserEntity entity = new UserEntity();

        entity.setId(null);
        entity.setFirstName(requestModel.getFirstName());
        entity.setLastName(requestModel.getLastName());
        entity.setCreationDate(LocalDateTime.now());

        var savedEntity = userRepository.save(entity);

        UserResponseModel user = new UserResponseModel();

        user.setId(savedEntity.getId());
        user.setFirstName(savedEntity.getFirstName());
        user.setLastName(savedEntity.getLastName());
        user.setCreationDate(savedEntity.getCreationDate());

        return user;
    }

    public UserResponseModel update (UUID id, UserRequestModel requestModel){

        userRequestValidator.validate(requestModel);

        if (!userRepository.existsById(id)) return null;

        UserEntity entity = new UserEntity();

        entity.setId(id);
        entity.setFirstName(requestModel.getFirstName());
        entity.setLastName(requestModel.getLastName());
        entity.setCreationDate(userRepository.getReferenceById(id).getCreationDate());

        var savedEntity = userRepository.save(entity);

        UserResponseModel user = new UserResponseModel();

        user.setId(savedEntity.getId());
        user.setFirstName(savedEntity.getFirstName());
        user.setLastName(savedEntity.getLastName());
        user.setCreationDate(savedEntity.getCreationDate());

        return user;
    }

    public boolean deleteById (UUID id){
        if (!userRepository.existsById(id)) return false;

        userRepository.deleteById(id);

        return true;
    }
}