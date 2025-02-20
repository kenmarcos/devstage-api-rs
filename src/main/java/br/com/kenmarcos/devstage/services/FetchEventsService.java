package br.com.kenmarcos.devstage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.devstage.entities.EventEntity;
import br.com.kenmarcos.devstage.repositories.EventRepository;
@Service

public class FetchEventsService {
  @Autowired
  private EventRepository eventRepository;

  public List<EventEntity> execute() {
    List<EventEntity> events = eventRepository.findAll();

    return events;
  }
}
