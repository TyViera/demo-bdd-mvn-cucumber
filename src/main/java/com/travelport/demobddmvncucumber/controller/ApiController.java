package com.travelport.demobddmvncucumber.controller;

import com.travelport.demobddmvncucumber.model.Operation;
import com.travelport.demobddmvncucumber.model.TransferRequest;
import com.travelport.demobddmvncucumber.service.FraudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/operations/fraudulent")
@RestController
@RequiredArgsConstructor
public class ApiController {

  private final FraudService fraudService;

  @PostMapping
  public Operation checkOperation(@RequestBody @Valid TransferRequest transferRequest) {
    return fraudService.checkOperation(transferRequest);
  }
}
