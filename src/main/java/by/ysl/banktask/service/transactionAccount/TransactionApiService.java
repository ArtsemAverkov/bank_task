package by.ysl.banktask.service.transactionAccount;

import by.ysl.banktask.entity.Account;
import by.ysl.banktask.repository.AccountRepository;
import by.ysl.banktask.service.accountOperation.AccountOperationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.Semaphore;

@Service
@RequiredArgsConstructor
public class TransactionApiService implements TransactionService {

    private final AccountRepository accountRepository;
    private final AccountOperationService accountOperationService;
    private final Semaphore semaphore = new Semaphore(2);



    @Override
    public boolean withdraw(Long accountId, BigDecimal amount) {
        try {
            semaphore.acquire();
            Optional<Account> byId = accountRepository.findById(accountId);
            if (byId.isEmpty()) {
                throw new EntityNotFoundException("Account not found");
            }
            Account account = byId.get();
            if (account.getBalance().compareTo(amount) < 0) {
                throw  new IllegalArgumentException("Insufficient funds");
            }
            account.setBalance(account.getBalance().subtract(amount));
            accountRepository.save(account);

            accountOperationService.withdraw(account, amount);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
        return true;
    }

    @Override
    public boolean deposit(Long accountId, BigDecimal amount) {
        try {
            semaphore.acquire();
            Optional<Account> byId = accountRepository.findById(accountId);
            if (byId.isEmpty()) {
                throw new EntityNotFoundException("Account not found");
            }
            Account account = byId.get();
            account.setBalance(account.getBalance().add(amount));
            accountRepository.save(account);
            accountOperationService.deposit(account, amount);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
        return true;
    }
}
