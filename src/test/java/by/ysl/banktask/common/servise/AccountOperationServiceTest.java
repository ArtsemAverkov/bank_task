package by.ysl.banktask.common.servise;

import by.ysl.banktask.common.extension.ValidParameterResolverAccount;
import by.ysl.banktask.common.extension.ValidParameterResolverAccountOperation;
import by.ysl.banktask.common.utill.UserId;
import by.ysl.banktask.entity.Account;
import by.ysl.banktask.entity.AccountOperation;
import by.ysl.banktask.repository.AccountOperationRepository;
import by.ysl.banktask.service.accountOperation.AccountOperationApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Account Operation Service Test")
@ExtendWith({MockitoExtension.class,
        ValidParameterResolverAccount.class,
        ValidParameterResolverAccountOperation.class})
public class AccountOperationServiceTest {

        @InjectMocks
        private AccountOperationApiService accountOperationApiService;

        @Mock
        private AccountOperationRepository accountOperationRepository;


        @Test
        void shouldDepositWhenDepositValid(AccountOperation accountOperation, Account account) {
            BigDecimal bigDecimal = new BigDecimal("10.20");
          accountOperationApiService.deposit(account, bigDecimal);
            verify(accountOperationRepository, times(1)).save(any(AccountOperation.class));
        }

        @Test
        void shouldWithdrawWhenWithdrawValid(AccountOperation accountOperation, Account account) {
            BigDecimal bigDecimal = new BigDecimal("10.20");
            accountOperationApiService.withdraw(account, bigDecimal);
            verify(accountOperationRepository, times(1)).save(any(AccountOperation.class));
        }

        @Test
        void shouldGetAllOperationWhenGetAllOperationValid(AccountOperation accountOperation, Account account) {
            List<AccountOperation> accountOperationList = new ArrayList<>();
            accountOperationList.add(accountOperation);
            when(accountOperationRepository.findAllByAccountId(UserId.VALUE_1.getValue())).thenReturn(accountOperationList);
            assertEquals(accountOperationList, accountOperationApiService.getAllOperation(UserId.VALUE_1.getValue()));

        }
    }
