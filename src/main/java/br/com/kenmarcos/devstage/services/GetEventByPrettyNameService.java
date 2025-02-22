package br.com.kenmarcos.devstage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.devstage.repositories.EventRepository;
import br.com.kenmarcos.devstage.entities.EventEntity;
import br.com.kenmarcos.devstage.exceptions.customExceptions.ResourceNotFoundException;


@Service
public class GetEventByPrettyNameService {
  @Autowired
  private EventRepository eventRepository;

  public EventEntity execute(String prettyName) {
    EventEntity event = eventRepository.findByPrettyName(prettyName);

    if (event == null) {
      throw new ResourceNotFoundException("Event with pretty name '" + prettyName + "' not found");
    }

    return event;
  }
}
