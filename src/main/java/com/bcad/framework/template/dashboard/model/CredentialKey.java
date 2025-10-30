package com.bcad.framework.template.dashboard.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(value = "credential_key", schema = "public")
public class CredentialKey {

    @Id
    private UUID id;

    @Column("code")
    private String code;

    @Column("company_code")
    private String companyCode;

    @Column("user_id")
    private String userId;

    @Column("password")
    private String password;

    @Column("create_date")
    private LocalDateTime createdDate;

    @Column("create_by")
    private String createdBy;

    @Column("last_update_date")
    private LocalDateTime lastUpdatedDate;

    @Column("last_update_by")
    private String lastUpdatedBy;
}

