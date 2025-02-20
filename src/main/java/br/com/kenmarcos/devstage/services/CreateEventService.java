package br.com.kenmarcos.devstage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.devstage.entities.EventEntity;
import br.com.kenmarcos.devstage.repositories.EventRepository;

@Service
public class CreateEventService {

  @Autowired
  private EventRepository eventRepository;

  public EventEntity execute (EventEntity eventEntity) {
    eventEntity.setPrettyName(eventEntity.getTitle().toLowerCase().replace(" ", "-"));

    return eventRepository.save(eventEntity);
  }
}
