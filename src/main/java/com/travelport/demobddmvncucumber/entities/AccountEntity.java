package com.travelport.demobddmvncucumber.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
  private String id;
  private String number;
  private BigDecimal balance;
  private LocalDateTime openingDate;
  private BigDecimal weeklyAverageTransactionAmount;
}
