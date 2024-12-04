package com.travelport.demobddmvncucumber.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransferRequest {
  @NotNull @Valid private Account sourceAccount;
  @Valid @NotNull private Account targetAccount;

  @NotNull
  @Min(0)
  @Max(1_000_000)
  private BigDecimal amount;
}
