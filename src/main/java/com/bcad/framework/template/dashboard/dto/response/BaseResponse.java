package com.bcad.framework.template.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    private boolean isFailure;
    private String path;
    private LocalDateTime timestamp;
    private DetailResponse detailResponse;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailResponse {
        private String code;
        private String message;
    }
}
