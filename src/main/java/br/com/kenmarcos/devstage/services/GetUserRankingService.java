package br.com.kenmarcos.devstage.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.devstage.dtos.UserRankingDTO;
import br.com.kenmarcos.devstage.dtos.SubscriptionRankingItemDTO;
import br.com.kenmarcos.devstage.exceptions.customExceptions.ResourceNotFoundException;

@Service
public class GetUserRankingService {

  @Autowired
  private GetCompleteRankingService getCompleteRankingService;
    
  public UserRankingDTO execute (String prettyName, UUID userId) {
    List<SubscriptionRankingItemDTO> ranking = getCompleteRankingService.execute(prettyName);

    SubscriptionRankingItemDTO rankingItem = ranking.stream().filter(i->i.getUserId().equals(userId.toString())).findFirst().orElse(null);
    if (rankingItem == null) {
      throw new ResourceNotFoundException("Subscription with user indication '" + userId + "' not found");
    }

    Integer position = IntStream.range(0, ranking.size()).filter(pos->ranking.get(pos).getUserId().equals(userId.toString())).findFirst().getAsInt();

    return new UserRankingDTO(rankingItem, position+1);
  }
}
