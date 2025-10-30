package com.bcad.framework.template.dashboard.service;

import com.bcad.framework.template.dashboard.dto.request.InsertUpdateKafkaConfigRequest;
import com.bcad.framework.template.dashboard.dto.response.GetKafkaConfigResponse;
import com.bcad.framework.template.dashboard.dto.response.InsertUpdateKafkaConfigResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

public interface KafkaConfigService {
    Mono<GetKafkaConfigResponse> getConfigKafka(String code, int offset, int limit, ServerWebExchange exchange);

    Mono<InsertUpdateKafkaConfigResponse> insertOrUpdateConfigKafka(List<InsertUpdateKafkaConfigRequest> request, ServerWebExchange exchange);

}
