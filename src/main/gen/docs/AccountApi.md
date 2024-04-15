# AccountApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deposit**](AccountApi.md#deposit) | **POST** /account/{id}/deposit | Deposit funds to account |
| [**getAllOperation**](AccountApi.md#getAllOperation) | **GET** /account/{id}/all_operation | Get all account operations |
| [**getBalance**](AccountApi.md#getBalance) | **GET** /account/{id}/balance | Get account balance |
| [**withdraw**](AccountApi.md#withdraw) | **POST** /account/{id}/withdraw | Withdraw funds from account |


<a name="deposit"></a>
# **deposit**
> deposit(id, amount)

Deposit funds to account

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AccountApi apiInstance = new AccountApi(defaultClient);
    Integer id = 56; // Integer | Account ID
    BigDecimal amount = new BigDecimal(78); // BigDecimal | Amount to deposit
    try {
      apiInstance.deposit(id, amount);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#deposit");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**| Account ID | |
| **amount** | **BigDecimal**| Amount to deposit | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Operation successful |  -  |

<a name="getAllOperation"></a>
# **getAllOperation**
> getAllOperation(id)

Get all account operations

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AccountApi apiInstance = new AccountApi(defaultClient);
    Integer id = 56; // Integer | Account ID
    try {
      apiInstance.getAllOperation(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#getAllOperation");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**| Account ID | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | All account operations retrieved successfully |  -  |

<a name="getBalance"></a>
# **getBalance**
> getBalance(id)

Get account balance

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AccountApi apiInstance = new AccountApi(defaultClient);
    Integer id = 56; // Integer | Account ID
    try {
      apiInstance.getBalance(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#getBalance");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**| Account ID | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Account balance retrieved successfully |  -  |

<a name="withdraw"></a>
# **withdraw**
> withdraw(id, amount)

Withdraw funds from account

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AccountApi apiInstance = new AccountApi(defaultClient);
    Integer id = 56; // Integer | Account ID
    BigDecimal amount = new BigDecimal(78); // BigDecimal | Amount to withdraw
    try {
      apiInstance.withdraw(id, amount);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#withdraw");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**| Account ID | |
| **amount** | **BigDecimal**| Amount to withdraw | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Operation successful |  -  |

