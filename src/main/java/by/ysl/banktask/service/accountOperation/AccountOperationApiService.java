package by.ysl.banktask.service.accountOperation;

import by.ysl.banktask.entity.Account;
import by.ysl.banktask.entity.AccountOperation;
import by.ysl.banktask.repository.AccountOperationRepository;
import by.ysl.banktask.utill.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountOperationApiService implements AccountOperationService{

    private final AccountOperationRepository accountOperationRepository;

    @Transactional
    public void deposit(Account account, BigDecimal amount) {
        AccountOperation operation = new AccountOperation();
        operation.setAccount(account);
        operation.setOperationType(OperationType.DEPOSIT);
        operation.setAmount(amount);
        operation.setTimestamp(LocalDateTime.now());
        accountOperationRepository.save(operation);
    }

    @Transactional
    public void withdraw(Account account, BigDecimal amount) {
        AccountOperation operation = new AccountOperation();
        operation.setAccount(account);
        operation.setOperationType(OperationType.WITHDRAWAL);
        operation.setAmount(amount);
        operation.setTimestamp(LocalDateTime.now());
        accountOperationRepository.save(operation);
    }

    @Override
    public List<AccountOperation> getAllOperation(Long id) {
        return accountOperationRepository.findAllByAccountId(id);
    }
}