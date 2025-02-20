package br.com.kenmarcos.devstage.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kenmarcos.devstage.entities.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, UUID> {
  public EventEntity findByPrettyName(String prettyName);
}
