package com.travelport.demobddmvncucumber.repository.impl;

import com.travelport.demobddmvncucumber.entities.AccountEntity;
import com.travelport.demobddmvncucumber.repository.TransfersRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransfersRepositoryImpl implements TransfersRepository {

  private final Database database;

  @Override
  public BigDecimal getWeeklyAverage(String accountNumber) {
    return database
        .getAccount(accountNumber)
        .map(AccountEntity::getWeeklyAverageTransactionAmount)
        .orElseThrow();
  }
}
