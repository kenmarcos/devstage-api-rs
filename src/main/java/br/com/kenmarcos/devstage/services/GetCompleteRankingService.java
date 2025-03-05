package br.com.kenmarcos.devstage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.devstage.dtos.SubscriptionRankingItemDTO;
import br.com.kenmarcos.devstage.entities.EventEntity;
import br.com.kenmarcos.devstage.exceptions.customExceptions.ResourceNotFoundException;
import br.com.kenmarcos.devstage.repositories.EventRepository;
import br.com.kenmarcos.devstage.repositories.SubscriptionRepository;

@Service
public class GetCompleteRankingService {

  @Autowired
  private SubscriptionRepository subscriptionRepository;

  @Autowired
  private EventRepository eventRepository;
  
  public List<SubscriptionRankingItemDTO> execute (String prettyName) {
    EventEntity event = eventRepository.findByPrettyName(prettyName);

    if (event == null) { 
      throw new ResourceNotFoundException("Ranking do evento '" + prettyName + "' não existe");
    }

    List<SubscriptionRankingItemDTO> ranking = subscriptionRepository.generateRanking(event.getId()).subList(0, 3);

    return ranking;
  }
}
