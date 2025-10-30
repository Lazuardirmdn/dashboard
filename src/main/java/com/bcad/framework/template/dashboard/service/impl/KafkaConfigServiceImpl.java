package com.bcad.framework.template.dashboard.service.impl;

import com.bcad.framework.template.dashboard.dto.request.InsertUpdateKafkaConfigRequest;
import com.bcad.framework.template.dashboard.dto.response.GetKafkaConfigResponse;
import com.bcad.framework.template.dashboard.dto.response.InsertUpdateKafkaConfigResponse;
import com.bcad.framework.template.dashboard.repository.KafkaConfigRepository;
import com.bcad.framework.template.dashboard.service.KafkaConfigService;
import com.bcad.framework.template.dashboard.transform.KafkaConfigTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConfigServiceImpl implements KafkaConfigService {

    private final KafkaConfigRepository kafkaConfigRepository;
    private final KafkaConfigTransform kafkaConfigTransform;

    @Override
    public Mono<GetKafkaConfigResponse> getConfigKafka(String code, int offset, int limit, ServerWebExchange exchange) {
        log.info("[START] getConfigKafka with method {} on endpoint {}",
                exchange.getRequest().getMethod(),
                exchange.getRequest().getURI().getPath());

        return kafkaConfigRepository.findAll()
                .filter(config -> code == null || config.getCode().equalsIgnoreCase(code))
                .skip(offset)
                .take(limit)
                .map(config -> {
                    GetKafkaConfigResponse.KafkaConfigResponseDetail detail = new GetKafkaConfigResponse.KafkaConfigResponseDetail();
                    detail.setId(config.getId().toString());
                    detail.setCode(config.getCode());
                    detail.setTopicName(config.getTopicName());
                    return detail;
                })
                .collectList()
                .map(details -> kafkaConfigTransform.getConfigKafkaTransformSuccess(details, exchange))
                .doOnSuccess(resp -> log.info("[END] getConfigKafka success"))
                .onErrorResume(error -> {
                    log.error("[ERROR] getConfigKafka", error);
                    return Mono.just(kafkaConfigTransform.getConfigKafkaTransformFailed(error.getMessage(), exchange));
                });
    }


    @Override
    public Mono<InsertUpdateKafkaConfigResponse> insertOrUpdateConfigKafka(List<InsertUpdateKafkaConfigRequest> requestList, ServerWebExchange exchange) {
        log.info("[START] insertOrUpdateConfigKafka with method {} on endpoint {}",
                exchange.getRequest().getMethod(),
                exchange.getRequest().getURI().getPath());

        return Flux.fromIterable(requestList)
                .flatMap(request ->
                        kafkaConfigRepository.findByCode(request.getCode())
                                .flatMap(existingConfig -> {
                                    log.info("[INFO] update data kafka config");
                                    return Mono.just(kafkaConfigTransform.toEntityKafkaConfig(request, existingConfig));
                                })
                                .switchIfEmpty(Mono.defer(() -> {
                                            log.info("[INFO] insert data kafka config");
                                            return Mono.just(kafkaConfigTransform.toEntityKafkaConfig(request, null));
                                        })
                                )
                )
                .collectList()
                .flatMapMany(kafkaConfigRepository::saveAll)
                .collectList()
                .map(savedList -> kafkaConfigTransform.updateConfigKafkaTransformSuccess(exchange, savedList))
                .doOnSuccess(resp -> log.info("[END] insertOrUpdateConfigKafka success"))
                .onErrorResume(error -> {
                    log.error("[ERROR] insertOrUpdateConfigKafka");
                    return Mono.just(kafkaConfigTransform.updateConfigKafkaTransformFailed(exchange));
                });
    }
}
