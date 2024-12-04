package com.travelport.demobddmvncucumber;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.travelport.demobddmvncucumber.entities.AccountEntity;
import com.travelport.demobddmvncucumber.repository.impl.Database;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

public class CucumberStepDefs extends SpringCucumberTest {

  private static ValidatableResponse action;

  @LocalServerPort private Integer port;
  @Autowired private Database database;

  @Given("the opening date of the source account is today")
  public void theOpeningDateOfTheSourceAccountIsToday() {
    database.putAccount(AccountEntity.builder().number("7777-7777-7777-7777").build());
  }

  @When("the user check if one operation is fraudulent")
  public void theUserCheckIfOneOperationIsFraudulent() {
    // call http service

    action =
        given()
            .port(port)
            .log()
            .all()
            .when()
            .body(
                """
{
    "sourceAccount": {
        "number": "7777-7777-7777-7777"
    },
    "targetAccount": {
        "number": "9999-9999-9999-9999"
    },
    "amount": 12
}
""")
            .contentType(ContentType.JSON)
            .post("/operations/fraudulent")
            .then();
  }

  @Then("the operation must be marked as fraudulent")
  public void theOperationMustBeMarkedAsFraudulent() {
    // read http response
    // Data source - check if data changes
    action.log().all().statusCode(200).body("isFraudulent", equalTo(true));
  }

  @Given("the weekly average amount of the account {string} is {int} with opening date {string}")
  public void theWeeklyAverageAmountOfTheAccountIsWithOpeningDate(
      String accountNUmber, int weeklyAmount, String openingDate) {
    database.putAccount(
        AccountEntity.builder()
            .number(accountNUmber)
            .weeklyAverageTransactionAmount(new BigDecimal(weeklyAmount))
            .openingDate(LocalDateTime.parse(openingDate))
            .build());
  }

  @And("the opening date of the account {string} is {string}")
  public void theOpeningDateOfTheAccountIs(String accountNUmber, String openingDate) {
    database.putAccount(
        AccountEntity.builder()
            .number(accountNUmber)
            .openingDate(LocalDateTime.parse(openingDate))
            .build());
  }

  @When(
      "the user check if one operation is fraudulent with source account {string} and target account {string} and amount {int}")
  public void theUserCheckIfOneOperationIsFraudulentWithSourceAccountAndTargetAccountAndAmount(
      String sourceAccount, String targetAccount, int amount) {
    // call http service
    action =
        given()
            .port(port)
            .log()
            .all()
            .when()
            .body(
                """
{
    "sourceAccount": {
        "number": "%s"
    },
    "targetAccount": {
        "number": "%s"
    },
    "amount": %d
}
"""
                    .formatted(sourceAccount, targetAccount, amount))
            .contentType(ContentType.JSON)
            .post("/operations/fraudulent")
            .then();
  }
}
