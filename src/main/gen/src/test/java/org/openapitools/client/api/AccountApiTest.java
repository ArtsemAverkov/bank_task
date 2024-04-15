/*
 * Bank Task API
 * This API provides endpoints for managing bank operations and accounts. It allows users to deposit and withdraw funds, as well as retrieve account information. The API uses PostgreSQL as the database backend and Liquibase for database migration. 
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for AccountApi
 */
@Disabled
public class AccountApiTest {

    private final AccountApi api = new AccountApi();

    /**
     * Deposit funds to account
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void depositTest() throws ApiException {
        Integer id = null;
        BigDecimal amount = null;
        api.deposit(id, amount);
        // TODO: test validations
    }

    /**
     * Get all account operations
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getAllOperationTest() throws ApiException {
        Integer id = null;
        api.getAllOperation(id);
        // TODO: test validations
    }

    /**
     * Get account balance
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getBalanceTest() throws ApiException {
        Integer id = null;
        api.getBalance(id);
        // TODO: test validations
    }

    /**
     * Withdraw funds from account
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void withdrawTest() throws ApiException {
        Integer id = null;
        BigDecimal amount = null;
        api.withdraw(id, amount);
        // TODO: test validations
    }

}
