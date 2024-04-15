package by.ysl.banktask.service.accountOperation;

import java.math.BigDecimal;
import java.util.List;

import by.ysl.banktask.entity.Account;
import by.ysl.banktask.entity.AccountOperation;

public interface AccountOperationService {
    void deposit(Account account, BigDecimal amount);
    void withdraw(Account account, BigDecimal amount);
    List<AccountOperation> getAllOperation(Long id);
}
