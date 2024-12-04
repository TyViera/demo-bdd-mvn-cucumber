package com.travelport.demobddmvncucumber.repository;

import java.math.BigDecimal;

public interface TransfersRepository {
  BigDecimal getWeeklyAverage(String accountNumber);
}
