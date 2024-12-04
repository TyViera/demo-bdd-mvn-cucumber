package com.travelport.demobddmvncucumber.repository.impl;

import com.travelport.demobddmvncucumber.entities.AccountEntity;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class Database {

  private final List<AccountEntity> accounts = new ArrayList<>(100);

  @PostConstruct
  public void afterInit() {
    putAccount(
        AccountEntity.builder()
            .number("1234-1234-1234-1234")
            .openingDate(LocalDateTime.now().minusDays(50))
            .weeklyAverageTransactionAmount(BigDecimal.TEN)
            .build());
    putAccount(
        AccountEntity.builder()
            .number("9999-9999-9999-9999")
            .openingDate(LocalDateTime.now().minusDays(50))
            .build());
  }

  public Optional<AccountEntity> getAccount(String accountNumber) {
    return accounts.stream().filter(x -> Objects.equals(x.getNumber(), accountNumber)).findFirst();
  }

  public void putAccount(AccountEntity account) {
    if (account.getId() == null) {
      account.setId(UUID.randomUUID().toString());
    }
    if (account.getOpeningDate() == null) {
      account.setOpeningDate(LocalDateTime.now());
    }
    if (account.getWeeklyAverageTransactionAmount() == null) {
      account.setWeeklyAverageTransactionAmount(BigDecimal.ZERO);
    }
    if (account.getBalance() == null) {
      account.setBalance(BigDecimal.ZERO);
    }
    accounts.add(account);
  }
}
