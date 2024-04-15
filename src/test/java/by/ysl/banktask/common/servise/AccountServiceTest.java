package by.ysl.banktask.common.servise;

import by.ysl.banktask.common.extension.ValidParameterResolverAccount;
import by.ysl.banktask.common.extension.ValidParameterResolverUser;
import by.ysl.banktask.common.utill.UserId;
import by.ysl.banktask.entity.Account;
import by.ysl.banktask.entity.User;
import by.ysl.banktask.repository.AccountRepository;
import by.ysl.banktask.service.account.AccountApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@DisplayName("Account Service Test")
public class AccountServiceTest {

    @Nested
    @ExtendWith({MockitoExtension.class,
            ValidParameterResolverUser.class,
            ValidParameterResolverAccount.class})
    public class ValidData {

        @InjectMocks
        private AccountApiService accountApiService;

        @Mock
        private AccountRepository accountRepository;


        @Test
        void shouldReadAccountWhenAccountValid(Account account) {
            when(accountRepository.findById(UserId.VALUE_1.getValue())).
                    thenReturn(Optional.ofNullable(account));
            assertEquals(account, accountApiService.read(UserId.VALUE_1.getValue()));
            verify(accountRepository, times(1)).findById(UserId.VALUE_1.getValue());
        }

        @Test
        void shouldCreateAccountWhenUAccountIsValid(Account account, User user) {
            when(accountRepository.save(any(Account.class))).thenReturn(account);
            accountApiService.create(user);
            assertEquals(account.getId(), accountApiService.create(user));
        }

        @Test
        void shouldDeleteAccountWhenAccountIsValid (Account account) {
            when(accountRepository.findById(UserId.VALUE_1.getValue())).thenReturn(Optional.of(account));
            accountApiService.delete(UserId.VALUE_1.getValue());
            verify(accountRepository, times(1)).deleteById(UserId.VALUE_1.getValue());
        }

        @Test
        void shouldGetBalanceAccountWhenGetBalanceIsValid (Account account) {
            when(accountRepository.findById(UserId.VALUE_1.getValue())).thenReturn(Optional.of(account));
            accountApiService.getBalance(UserId.VALUE_1.getValue());
            verify(accountRepository, times(1)).findById(UserId.VALUE_1.getValue());
        }
    }

    @Nested
    @ExtendWith(MockitoExtension.class)
    public class InvalidData {

        @InjectMocks
        private AccountApiService accountApiService;

        @Mock
        private AccountRepository accountRepository;

        @Test
        void shouldReadUserWhenUserInvalid() {
            when(accountRepository.findById(UserId.VALUE_1.getValue())).thenReturn(Optional.ofNullable(null));
            assertThrows(IllegalArgumentException.class, () -> accountApiService.read(UserId.VALUE_1.getValue()));
        }
    }
}
