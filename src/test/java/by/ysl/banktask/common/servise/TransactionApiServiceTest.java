package by.ysl.banktask.common.servise;

import by.ysl.banktask.common.extension.ValidParameterResolverAccount;
import by.ysl.banktask.common.extension.ValidParameterResolverUser;
import by.ysl.banktask.common.utill.UserId;
import by.ysl.banktask.entity.Account;
import by.ysl.banktask.repository.AccountRepository;
import by.ysl.banktask.service.accountOperation.AccountOperationService;
import by.ysl.banktask.service.transactionAccount.TransactionApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Transaction Service Test")
public class TransactionApiServiceTest {

    @Nested
    @ExtendWith({MockitoExtension.class,
            ValidParameterResolverUser.class,
            ValidParameterResolverAccount.class})
    public class ValidData {

        @InjectMocks
        private TransactionApiService transactionApiService;

        @Mock
        private AccountRepository accountRepository;

        @Mock
        AccountOperationService accountOperationService;


        @Test
        void shouldWithdrawWhenWithdrawValid() {
            Account mockAccount = new Account();
            mockAccount.setId(UserId.VALUE_1.getValue());
            mockAccount.setBalance(new BigDecimal("1000.00"));
            when(accountRepository.findById(UserId.VALUE_1.getValue())).thenReturn(Optional.of(mockAccount));
            boolean resultFound = transactionApiService.withdraw(1L, new BigDecimal("50.00"));
            verify(accountRepository, times(1)).findById(UserId.VALUE_1.getValue());
            verify(accountRepository, times(1)).save(any(Account.class));
            verify(accountOperationService, times(1)).withdraw(any(Account.class), any(BigDecimal.class));
            assertTrue(resultFound);
        }

        @Test
        void shouldDepositWhenDepositValid() {
            Account mockAccount = new Account();
            mockAccount.setId(UserId.VALUE_1.getValue());
            mockAccount.setBalance(new BigDecimal("1000.00"));
            when(accountRepository.findById(UserId.VALUE_1.getValue())).thenReturn(Optional.of(mockAccount));
            boolean result = transactionApiService.deposit(1L, new BigDecimal("100.00"));
            verify(accountRepository, times(1)).findById(UserId.VALUE_1.getValue());
            verify(accountRepository, times(1)).save(any(Account.class));
            verify(accountOperationService, times(1)).deposit(any(Account.class), any(BigDecimal.class));
            assertTrue(result);
        }

        @Test
        public void testConcurrentWithdrawAndDeposit(){
            int numThreads = 2;
            Semaphore semaphore = new Semaphore(numThreads);

            ExecutorService executor = Executors.newFixedThreadPool(numThreads);
            for (int i = 0; i < numThreads; i++) {
                executor.submit(() -> {
                    try {
                        semaphore.acquire();
                        transactionApiService.withdraw(1L, BigDecimal.TEN);
                        transactionApiService.deposit(1L, BigDecimal.TEN);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        semaphore.release();
                    }
                });
            }
            executor.shutdown();
        }
}
}
