package com.caldeira.projetofinal.user.repositories;

import com.caldeira.projetofinal.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    // interface vazia, pq herda os métodos de JpaRepository
}