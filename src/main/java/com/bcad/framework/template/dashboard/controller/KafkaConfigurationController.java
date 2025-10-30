package com.bcad.framework.template.dashboard.controller;

import com.bcad.framework.template.dashboard.dto.request.InsertUpdateKafkaConfigRequest;
import com.bcad.framework.template.dashboard.dto.response.GetKafkaConfigResponse;
import com.bcad.framework.template.dashboard.dto.response.InsertUpdateKafkaConfigResponse;
import com.bcad.framework.template.dashboard.service.KafkaConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/kafka-config")
@RequiredArgsConstructor
public class KafkaConfigurationController {

    private final KafkaConfigService kafkaConfigService;

    @GetMapping("/get")
    public Mono<GetKafkaConfigResponse> getKafkaConfig(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            ServerWebExchange exchange
    ) {
        return kafkaConfigService.getConfigKafka(code, offset, limit, exchange);
    }


    @PostMapping("/insert-update")
    public Mono<InsertUpdateKafkaConfigResponse> updateKafkaConfig(@RequestBody List<InsertUpdateKafkaConfigRequest> request, ServerWebExchange exchange) {
        return kafkaConfigService.insertOrUpdateConfigKafka(request, exchange);
    }

}
