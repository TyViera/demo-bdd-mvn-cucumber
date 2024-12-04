package com.travelport.demobddmvncucumber.repository.impl;

import com.travelport.demobddmvncucumber.entities.AccountEntity;
import com.travelport.demobddmvncucumber.repository.AccountRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

  private final Database database;

  @Override
  public LocalDateTime getOpeningDate(String accountNumber) {
    return database.getAccount(accountNumber).map(AccountEntity::getOpeningDate).orElseThrow();
  }
}
