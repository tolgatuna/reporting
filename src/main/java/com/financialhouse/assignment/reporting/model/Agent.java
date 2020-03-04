package com.financialhouse.assignment.reporting.model;

import lombok.Data;

@Data
public class Agent {
    private Integer id;
    private String customerIp;
    private String customerUserAgent;
    private String merchantIp;
    private String merchantUserAgent;
    private String created_at;
    private String updated_at;
    private String deleted_at;
}
