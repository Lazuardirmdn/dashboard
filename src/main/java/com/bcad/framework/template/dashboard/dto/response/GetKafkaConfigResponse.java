package com.bcad.framework.template.dashboard.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetKafkaConfigResponse extends BaseResponse {

    List<KafkaConfigResponseDetail> kafkaConfigResponseDetail;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KafkaConfigResponseDetail {
        private String id;
        private String code;
        private String topicName;

    }
}
