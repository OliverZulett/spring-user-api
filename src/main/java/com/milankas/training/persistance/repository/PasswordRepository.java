package com.milankas.training.persistance.repository;

import com.milankas.training.persistance.entity.PasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PasswordRepository extends JpaRepository<PasswordEntity, UUID> {

    PasswordEntity findByUserIdAndStatus(UUID user_id, Integer status);

}
