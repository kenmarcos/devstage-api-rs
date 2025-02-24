package br.com.kenmarcos.devstage.controllers;

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
import br.com.kenmarcos.devstage.entities.EventEntity;
import br.com.kenmarcos.devstage.entities.UserEntity;
import br.com.kenmarcos.devstage.exceptions.customExceptions.ResourceAlreadyExistsException;
import br.com.kenmarcos.devstage.exceptions.customExceptions.ResourceNotFoundException;
import br.com.kenmarcos.devstage.services.CreateSubscriptionService;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/subscription")
public class SubscriptionController {
  @Autowired
  private CreateSubscriptionService createSubscriptionService;
  
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
}
