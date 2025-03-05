package br.com.kenmarcos.devstage.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kenmarcos.devstage.dtos.SubscriptionRankingItemDTO;
import br.com.kenmarcos.devstage.entities.EventEntity;
import br.com.kenmarcos.devstage.entities.SubscriptionEntity;
import br.com.kenmarcos.devstage.entities.UserEntity;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {
  public SubscriptionEntity findByEventAndSubscriber(EventEntity event, UserEntity subscriber);

  @Query(value = "select tu.name, count(ts.id) as quantidade" + 
                " from tbl_subscription as ts inner join tbl_user as tu" + 
                " on ts.indication_user_id = tu.id " + 
                " where ts.indication_user_id is not null" + 
                "    and ts.event_id = :eventId" + 
                " group by tu.name" + 
                " order by quantidade desc", nativeQuery = true)
  public List<SubscriptionRankingItemDTO> generateRanking(@Param("eventId") UUID eventId);

}
