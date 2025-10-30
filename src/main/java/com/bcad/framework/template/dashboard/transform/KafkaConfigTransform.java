package com.bcad.framework.template.dashboard.transform;

import com.bcad.framework.template.dashboard.constant.ErrorConstant;
import com.bcad.framework.template.dashboard.dto.request.InsertUpdateKafkaConfigRequest;
import com.bcad.framework.template.dashboard.dto.response.BaseResponse;
import com.bcad.framework.template.dashboard.dto.response.GetKafkaConfigResponse;
import com.bcad.framework.template.dashboard.dto.response.InsertUpdateKafkaConfigResponse;
import com.bcad.framework.template.dashboard.model.KafkaConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface KafkaConfigTransform {

    default GetKafkaConfigResponse getConfigKafkaTransformSuccess(List<GetKafkaConfigResponse.KafkaConfigResponseDetail> details, ServerWebExchange exchange) {
        GetKafkaConfigResponse response = new GetKafkaConfigResponse();
        response.setKafkaConfigResponseDetail(details);
        response.setDetailResponse(new BaseResponse.DetailResponse(
                ErrorConstant.SUCCESS.getCode(),
                ErrorConstant.SUCCESS.getMessage()
        ));
        response.setFailure(false);
        response.setTimestamp(LocalDateTime.now());
        response.setPath(exchange.getRequest().getURI().getPath());

        return response;
    }


    default GetKafkaConfigResponse getConfigKafkaTransformFailed(String message, ServerWebExchange exchange) {
        BaseResponse.DetailResponse detailResponse = new BaseResponse.DetailResponse();
        detailResponse.setCode(ErrorConstant.GENERIC_ERROR.getCode());
        detailResponse.setMessage(message);

        GetKafkaConfigResponse response = new GetKafkaConfigResponse();
        response.setPath(exchange.getRequest().getURI().getPath());
        response.setDetailResponse(detailResponse);
        response.setFailure(true);
        response.setTimestamp(LocalDateTime.now());


        return response;
    }

    default KafkaConfiguration toEntityKafkaConfig(InsertUpdateKafkaConfigRequest request, KafkaConfiguration entity) {
        if (entity == null) {
            KafkaConfiguration kafkaConfiguration = new KafkaConfiguration();
            kafkaConfiguration.setCode(request.getCode());
            kafkaConfiguration.setTopicName(request.getTopicName());
            kafkaConfiguration.setCreatedBy("SYSTEM");
            kafkaConfiguration.setCreatedDate(LocalDateTime.now());
            return kafkaConfiguration;
        }

        if (request.getCode() != null) {
            entity.setCode(request.getCode());
        }
        if (request.getTopicName() != null) {
            entity.setTopicName(request.getTopicName());
        }
        entity.setLastUpdatedBy("SYSTEM");
        entity.setLastUpdatedDate(LocalDateTime.now());
        return entity;
    }


    default InsertUpdateKafkaConfigResponse updateConfigKafkaTransformSuccess(ServerWebExchange exchange, List<KafkaConfiguration> configurationList) {
        InsertUpdateKafkaConfigResponse response = new InsertUpdateKafkaConfigResponse();

        BaseResponse.DetailResponse detailResponse = new BaseResponse.DetailResponse();
        detailResponse.setMessage(ErrorConstant.SUCCESS.getMessage());
        detailResponse.setCode(ErrorConstant.SUCCESS.getCode());

        response.setDetailResponse(detailResponse);
        response.setFailure(false);
        response.setPath(exchange.getRequest().getURI().getPath());
        response.setTimestamp(LocalDateTime.now());
        response.setSizeSuccess(configurationList.size());
        return response;
    }

    default InsertUpdateKafkaConfigResponse updateConfigKafkaTransformFailed(ServerWebExchange exchange) {
        InsertUpdateKafkaConfigResponse response = new InsertUpdateKafkaConfigResponse();

        BaseResponse.DetailResponse detailResponse = new BaseResponse.DetailResponse();
        detailResponse.setMessage(ErrorConstant.GENERIC_ERROR.getMessage());
        detailResponse.setCode(ErrorConstant.GENERIC_ERROR.getCode());

        response.setDetailResponse(detailResponse);
        response.setFailure(true);
        response.setPath(exchange.getRequest().getURI().getPath());
        response.setTimestamp(LocalDateTime.now());
        response.setSizeSuccess(0);
        return response;
    }
}
