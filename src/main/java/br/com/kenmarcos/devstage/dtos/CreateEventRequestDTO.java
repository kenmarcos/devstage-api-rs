package br.com.kenmarcos.devstage.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateEventRequestDTO {
  
  @NotNull(message = "Title is required")
  private String title;

  @NotNull(message = "Location is required")
  private String location;

  @NotNull(message = "Price is required")
  @Digits(integer = 10, fraction = 2, message = "Price must be a number")
  private Double price;

  @Future(message = "Start date must be in the future")
  private LocalDate startDate;

  @Future(message = "End date must be in the future")
  private LocalDate endDate;

  private LocalTime startTime;

  private LocalTime endTime;
}
