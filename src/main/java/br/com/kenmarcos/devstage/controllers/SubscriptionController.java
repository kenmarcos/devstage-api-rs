package br.com.kenmarcos.devstage.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kenmarcos.devstage.dtos.CreateSubscriptionRequestDTO;
import br.com.kenmarcos.devstage.dtos.CreateSubscriptionResponseDTO;
import br.com.kenmarcos.devstage.dtos.SubscriptionRankingItemDTO;
import br.com.kenmarcos.devstage.dtos.UserRankingDTO;
import br.com.kenmarcos.devstage.entities.UserEntity;
import br.com.kenmarcos.devstage.exceptions.customExceptions.ResourceAlreadyExistsException;
import br.com.kenmarcos.devstage.exceptions.customExceptions.ResourceNotFoundException;
import br.com.kenmarcos.devstage.services.CreateSubscriptionService;
import br.com.kenmarcos.devstage.services.GetCompleteRankingService;
import br.com.kenmarcos.devstage.services.GetUserRankingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


@RestController()
@RequestMapping("/api/subscription")
public class SubscriptionController {
  @Autowired
  private CreateSubscriptionService createSubscriptionService;

  @Autowired
  private GetCompleteRankingService getCompleteRankingService;

  @Autowired
  private GetUserRankingService getUserRanking;
  
  @PostMapping({"/{prettyName}", "/{prettyName}/{indicatorId}"})
  public ResponseEntity<Object> createSubscription(
    @PathVariable String prettyName, 
    @Valid @RequestBody CreateSubscriptionRequestDTO createSubscriptionRequestDTO, 
    @PathVariable(required = false) UUID indicatorId
  ) {
    UserEntity subscriber = UserEntity.builder().name(createSubscriptionRequestDTO.getName()).email(createSubscriptionRequestDTO.getEmail()).build();
    
    try {
      CreateSubscriptionResponseDTO subscription = createSubscriptionService.execute(prettyName, subscriber, indicatorId);
      
      return ResponseEntity.ok().body(subscription);
    } catch (ResourceNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    } catch (ResourceAlreadyExistsException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    } catch (Exception ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  @GetMapping("/{prettyName}/ranking")
  public ResponseEntity<Object> generateRanking(@PathVariable String prettyName) {
      try {
        List<SubscriptionRankingItemDTO> ranking = getCompleteRankingService.execute(prettyName).subList(0, 3);

        return ResponseEntity.ok().body(ranking);
      } catch (ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
      } catch (Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
      }
  }

  @GetMapping("/{prettyName}/ranking/{userId}")
  public ResponseEntity<Object> generateRankingByEventAndUser(@PathVariable String prettyName, @PathVariable UUID userId) {
      try {
        UserRankingDTO userRanking = getUserRanking.execute(prettyName, userId);

        return ResponseEntity.ok().body(userRanking);
      } catch (ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
      } catch (Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
      }
  }
  
}
