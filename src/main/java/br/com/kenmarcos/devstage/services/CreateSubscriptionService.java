package br.com.kenmarcos.devstage.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.devstage.dtos.CreateSubscriptionResponseDTO;
import br.com.kenmarcos.devstage.entities.EventEntity;
import br.com.kenmarcos.devstage.entities.SubscriptionEntity;
import br.com.kenmarcos.devstage.entities.UserEntity;
import br.com.kenmarcos.devstage.exceptions.customExceptions.ResourceAlreadyExistsException;
import br.com.kenmarcos.devstage.exceptions.customExceptions.ResourceNotFoundException;
import br.com.kenmarcos.devstage.repositories.EventRepository;
import br.com.kenmarcos.devstage.repositories.SubscriptionRepository;
import br.com.kenmarcos.devstage.repositories.UserRepository;

@Service
public class CreateSubscriptionService {
  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SubscriptionRepository subscriptionRepository;

  public CreateSubscriptionResponseDTO execute (String prettyName, UserEntity subscriber, UUID indicatorId) {
    EventEntity event = eventRepository.findByPrettyName(prettyName);
    if (event == null) {
      throw new ResourceNotFoundException("Event with pretty name '" + prettyName + "' not found");
    }

    UserEntity user = userRepository.findByEmail(subscriber.getEmail());
    if (user == null) {
      user = userRepository.save(subscriber);
    }

    UserEntity indicator = null;
    if (indicatorId != null) {
      indicator = userRepository.findById(UUID.fromString(indicatorId.toString())).orElse(null);
      if (indicator == null) {
        throw new ResourceNotFoundException("Indicator with id '" + indicatorId + "' not found");
      }
    }
    
    SubscriptionEntity subscription = subscriptionRepository.findByEventAndSubscriber(event, subscriber);

    if (subscription != null) {
      throw new ResourceAlreadyExistsException("Subscription already exists for event '" + prettyName + "' and user '" + subscriber.getEmail() + "'");
    }

    SubscriptionEntity newSubscription = SubscriptionEntity
      .builder()
      .event(event)
      .subscriber(user)
      .indication(indicator)
      .build();

    SubscriptionEntity savedSubscription = subscriptionRepository.save(newSubscription);

    return new CreateSubscriptionResponseDTO(
      savedSubscription.getId(),
      "http://kenmarcos.com.br/subscription/" + savedSubscription.getEvent().getPrettyName() + "/" + savedSubscription.getSubscriber().getId()
    );
  }
}
