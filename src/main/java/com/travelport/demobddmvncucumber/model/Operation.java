package com.travelport.demobddmvncucumber.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Operation {

  public static Operation successOperation() {
    return new Operation(false);
  }

  public static Operation ofError(String errorMessage) {
    return new Operation(true, errorMessage);
  }

  private final Boolean isFraudulent;
  private String error;
}
