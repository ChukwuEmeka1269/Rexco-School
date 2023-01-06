package com.rexcoinc.rexcoschool.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;
}
