package com.bcad.framework.template.dashboard.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(value = "access_credential", schema = "public")
public class AccessCredential{

    @Id
    private UUID id;

    @Column("client_id")
    private String clientId;

    @Column("security_password")
    private String securityPassword;

    @Column("create_date")
    private LocalDateTime createdDate;

    @Column("create_by")
    private String createdBy;

    @Column("last_update_date")
    private LocalDateTime lastUpdatedDate;

    @Column("last_update_by")
    private String lastUpdatedBy;

    @Column("status")
    private Boolean status;
}
