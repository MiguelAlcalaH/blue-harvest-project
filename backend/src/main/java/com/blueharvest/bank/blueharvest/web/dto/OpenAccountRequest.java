package com.blueharvest.bank.blueharvest.web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OpenAccountRequest {
    private Long customerId;
    private BigDecimal initialCredit;
    
}