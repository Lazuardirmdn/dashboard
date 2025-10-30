package com.bcad.framework.template.dashboard.repository;

import com.bcad.framework.template.dashboard.model.KafkaConfiguration;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface KafkaConfigRepository extends ReactiveCrudRepository<KafkaConfiguration, UUID> {

    Mono<KafkaConfiguration> findByCode(String code);
}
