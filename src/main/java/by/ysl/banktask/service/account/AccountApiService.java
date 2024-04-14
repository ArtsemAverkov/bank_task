package by.ysl.banktask.service.account;

import by.ysl.banktask.entity.Account;
import by.ysl.banktask.entity.User;
import by.ysl.banktask.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountApiService implements AccountService{
    
    private final AccountRepository accountRepository;

    @Override
    public Account read(Long id) {
        return accountRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Invalid Account id:" + id));
    }

    @Override
    public long create(User user) {
        Account account = new Account();
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account).getId();
    }

    @Override
    public boolean delete(Long id) {
        read(id);
        accountRepository.deleteById(id);
        return false;
    }

    @Transactional(readOnly = true)
    public BigDecimal getBalance(Long accountId) {
        Account account = read(accountId);
        return account.getBalance();
    }
}
