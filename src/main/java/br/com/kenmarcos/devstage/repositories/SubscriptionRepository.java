package br.com.kenmarcos.devstage.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kenmarcos.devstage.entities.EventEntity;
import br.com.kenmarcos.devstage.entities.SubscriptionEntity;
import br.com.kenmarcos.devstage.entities.UserEntity;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {
  public SubscriptionEntity findByEventAndSubscriber(EventEntity event, UserEntity subscriber);
}
