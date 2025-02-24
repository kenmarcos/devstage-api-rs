package br.com.kenmarcos.devstage.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateSubscriptionResponseDTO {
  private UUID id;

  private String designation;
}
