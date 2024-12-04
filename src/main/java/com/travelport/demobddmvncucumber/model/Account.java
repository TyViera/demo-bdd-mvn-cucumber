package com.travelport.demobddmvncucumber.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Account {
  @NotNull
  @Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}")
  private String number;
}
