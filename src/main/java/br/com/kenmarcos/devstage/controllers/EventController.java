package br.com.kenmarcos.devstage.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kenmarcos.devstage.dtos.CreateEventDTO;
import br.com.kenmarcos.devstage.entities.EventEntity;
import br.com.kenmarcos.devstage.services.CreateEventService;
import br.com.kenmarcos.devstage.services.FetchEventsService;
import br.com.kenmarcos.devstage.services.GetEventByPrettyNameService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/events")
public class EventController {

  @Autowired
  private CreateEventService createEventService;
  @Autowired
  private FetchEventsService fetchEventsService;
  @Autowired
  private GetEventByPrettyNameService getEventByPrettyNameService;
  
  @PostMapping()
  public ResponseEntity<Object> createEvent(@RequestBody CreateEventDTO createEventDTO) {
    try {
      var eventEntity = EventEntity.builder()
      .title(createEventDTO.getTitle())
      .location(createEventDTO.getLocation())
      .price(createEventDTO.getPrice())
      .startDate(createEventDTO.getStartDate())
      .endDate(createEventDTO.getEndDate())
      .startTime(createEventDTO.getStartTime())
      .endTime(createEventDTO.getEndTime())
      .build();

      EventEntity event = createEventService.execute(eventEntity);
      return ResponseEntity.status(HttpStatus.CREATED).body(event);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping()
  public ResponseEntity<Object> fetchEvents () {
    try {
      List<EventEntity> events = fetchEventsService.execute();
  
      return ResponseEntity.ok().body(events);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/{prettyName}")
  public ResponseEntity<Object> getEventByPrettyName (@PathVariable String prettyName) {
    try {
      EventEntity event = getEventByPrettyNameService.execute(prettyName);
  
      return ResponseEntity.ok().body(event);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
