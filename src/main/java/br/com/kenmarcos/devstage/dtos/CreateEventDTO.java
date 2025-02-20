package br.com.kenmarcos.devstage.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateEventDTO {
  
  @NotNull(message = "Title is required")
  private String title;

  @NotNull(message = "Location is required")
  private String location;

  @NotNull(message = "Price is required")
  private Double price;

  private LocalDate startDate;

  private LocalDate endDate;

  private LocalTime startTime;

  private LocalTime endTime;
}
