package by.ysl.banktask.service.account;

import by.ysl.banktask.entity.Account;
import by.ysl.banktask.entity.User;

import java.math.BigDecimal;

public interface AccountService {
    Account read(Long id);
    long create(User user);
    boolean delete (Long id);
    BigDecimal getBalance(Long accountId);
}
