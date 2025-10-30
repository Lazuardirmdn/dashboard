package com.bcad.framework.template.dashboard.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InsertUpdateKafkaConfigResponse extends BaseResponse{
    private Integer sizeSuccess;
}
