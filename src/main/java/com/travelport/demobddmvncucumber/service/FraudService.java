package com.travelport.demobddmvncucumber.service;

import com.travelport.demobddmvncucumber.model.Operation;
import com.travelport.demobddmvncucumber.model.TransferRequest;

public interface FraudService {
  Operation checkOperation(TransferRequest transferRequest);
}
