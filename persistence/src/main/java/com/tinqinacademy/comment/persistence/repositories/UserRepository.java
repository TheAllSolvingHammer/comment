package com.tinqinacademy.comment.persistence.repositories;

import com.tinqinacademy.comment.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
