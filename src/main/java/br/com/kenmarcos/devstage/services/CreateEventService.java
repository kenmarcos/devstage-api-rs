package br.com.kenmarcos.devstage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.devstage.entities.EventEntity;
import br.com.kenmarcos.devstage.exceptions.customExceptions.ResourceAlreadyExistsException;
import br.com.kenmarcos.devstage.repositories.EventRepository;

@Service
public class CreateEventService {

  @Autowired
  private EventRepository eventRepository;

  public EventEntity execute (EventEntity eventEntity) {
    String prettyName = eventEntity.getTitle().toLowerCase().replace(" ", "-");

    EventEntity event = eventRepository.findByPrettyName(prettyName);

    if (event != null) {
      throw new ResourceAlreadyExistsException("Event with pretty name '" + prettyName + "' already exists");
    }

    eventEntity.setPrettyName(prettyName);

    return eventRepository.save(eventEntity);
  }
}
