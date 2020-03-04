package com.financialhouse.assignment.reporting.model;

import lombok.Data;

@Data
public class Ipn {
    private Boolean sent;
    private Merchant merchant;
}
