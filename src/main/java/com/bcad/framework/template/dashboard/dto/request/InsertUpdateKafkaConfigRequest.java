package com.bcad.framework.template.dashboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertUpdateKafkaConfigRequest {

    private String code;
    private String topicName;
}
