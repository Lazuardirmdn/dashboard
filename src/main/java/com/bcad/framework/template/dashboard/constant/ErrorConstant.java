package com.bcad.framework.template.dashboard.constant;

import lombok.Getter;

@Getter
public enum ErrorConstant {

    SUCCESS("SUS000","Success"),
    GENERIC_ERROR("ERR999",""),
    INVALID_TOKEN("ERR002","Invalid token"),
    DATA_NOT_FOUND("ERR001", "Data Not Found!");

    private final String code;
    private final String message;

    ErrorConstant(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
