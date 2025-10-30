package com.bcad.framework.template.dashboard.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(value = "kafka_configuration", schema = "public")
public class KafkaConfiguration {

    @Id
    private UUID id;

    @Column("code")
    private String code;

    @Column("topic_name")
    private String topicName;

    @Column("create_date")
    private LocalDateTime createdDate;

    @Column("create_by")
    private String createdBy;

    @Column("last_update_date")
    private LocalDateTime lastUpdatedDate;

    @Column("last_update_by")
    private String lastUpdatedBy;
}
