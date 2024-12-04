package com.travelport.demobddmvncucumber.repository;

import java.time.LocalDateTime;

public interface AccountRepository {
  LocalDateTime getOpeningDate(String accountNumber);
}
