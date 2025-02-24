package br.com.kenmarcos.devstage.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kenmarcos.devstage.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
  public UserEntity findByEmail(String email);
}
