package by.ysl.banktask.service.transactionAccount;

import java.math.BigDecimal;

public interface TransactionService {
   boolean deposit(Long accountId, BigDecimal amount);
   boolean withdraw(Long accountId, BigDecimal amount);
}
