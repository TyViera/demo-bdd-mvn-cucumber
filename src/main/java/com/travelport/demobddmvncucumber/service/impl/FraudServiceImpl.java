package com.travelport.demobddmvncucumber.service.impl;

import com.travelport.demobddmvncucumber.model.Account;
import com.travelport.demobddmvncucumber.model.Operation;
import com.travelport.demobddmvncucumber.model.TransferRequest;
import com.travelport.demobddmvncucumber.repository.AccountRepository;
import com.travelport.demobddmvncucumber.repository.TransfersRepository;
import com.travelport.demobddmvncucumber.service.FraudService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FraudServiceImpl implements FraudService {

  private final AccountRepository accountRepository;
  private final TransfersRepository transfersRepository;

  @Override
  public Operation checkOperation(TransferRequest request) {
    return checkOpeningDate(request.getSourceAccount())
        .or(() -> checkOpeningDate(request.getTargetAccount()))
        .or(() -> checkTransferAmount(request.getAmount(), request.getSourceAccount()))
        .or(this::checkTransferTime)
        .orElseGet(Operation::successOperation);
  }

  private Optional<Operation> checkTransferTime() {
    var now = LocalDateTime.now();
    if (now.getHour() < 4) {
      return Optional.of(Operation.ofError("Transfer time is not allowed"));
    }
    return Optional.empty();
  }

  private Optional<Operation> checkOpeningDate(Account account) {
    var sourceOpeningDate = accountRepository.getOpeningDate(account.getNumber());
    if (sourceOpeningDate.isAfter(LocalDateTime.now().minusDays(1))) {
      return Optional.of(Operation.ofError("Account is too new"));
    }
    return Optional.empty();
  }

  private Optional<Operation> checkTransferAmount(BigDecimal amount, Account account) {
    var doubleValue =
        transfersRepository.getWeeklyAverage(account.getNumber()).multiply(BigDecimal.TWO);
    if (amount.compareTo(doubleValue) >= 0) {
      return Optional.of(Operation.ofError("Transfer amount is too high"));
    }
    return Optional.empty();
  }
}
