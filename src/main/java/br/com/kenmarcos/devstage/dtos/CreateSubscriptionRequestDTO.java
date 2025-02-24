package br.com.kenmarcos.devstage.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSubscriptionRequestDTO {
  @NotNull(message = "Name is required")
  private String name;

  @NotNull(message = "E-mail is required")
  @Email(message = "Invalid e-mail")
  private String email;
}

