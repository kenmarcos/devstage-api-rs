package br.com.kenmarcos.devstage.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionRankingItemDTO {
  private String name;
  private Long subscribers;
}
