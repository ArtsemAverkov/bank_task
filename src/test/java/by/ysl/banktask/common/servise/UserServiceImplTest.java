package by.ysl.banktask.common.servise;

import by.ysl.banktask.common.extension.ValidParameterResolverAccount;
import by.ysl.banktask.common.extension.ValidParameterResolverUser;
import by.ysl.banktask.common.utill.UserId;
import by.ysl.banktask.entity.Account;
import by.ysl.banktask.entity.User;
import by.ysl.banktask.repository.AccountRepository;
import by.ysl.banktask.repository.UserRepository;
import by.ysl.banktask.service.account.AccountApiService;
import by.ysl.banktask.service.account.AccountService;
import by.ysl.banktask.service.user.UserApiService;
import by.ysl.banktask.service.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@DisplayName("User Service Test")
public class UserServiceImplTest {

    @Nested
    @ExtendWith({MockitoExtension.class,
            ValidParameterResolverUser.class,
            ValidParameterResolverAccount.class})
    public class ValidData {

        @InjectMocks
        private UserApiService userService;

        @Mock
        private UserRepository userRepository;

        @Mock
        AccountService accountService;

        @Test
        void shouldReadUserWhenUserValid(User user) {
            when(userRepository.findById(UserId.VALUE_1.getValue())).
                    thenReturn(Optional.ofNullable(user));
            assertEquals(user, userService.read(UserId.VALUE_1.getValue()));
            verify(userRepository, times(1)).findById(UserId.VALUE_1.getValue());
        }

        @Test
        void shouldCreateUserWhenUserIsValid(User user) {
            when(userRepository.save(any(User.class))).thenReturn(user);
            when(accountService.create(any(User.class))).thenReturn(UserId.VALUE_1.getValue());
            assertEquals(user.getId(), userService.create(user));
            verify(userRepository, times(1)).save(any(User.class));
        }

        @Test
        void shouldReadAllUserWhenUserIsValid(User user) {
            List<User> listUser = new ArrayList<>();
            listUser.add(user);

            Pageable pageable = PageRequest.of(0, 10, Sort.unsorted());

            when(userRepository.findAll(pageable))
                    .thenReturn(new PageImpl<>(listUser));
            assertEquals(listUser, userService.readAll(Pageable.ofSize(10).withPage(0)));
            verify(userRepository, times(1)).findAll(Pageable.ofSize(10).withPage(0));
        }


        @Test
        void shouldUpdateUserWhenUserIsValid(User user) {
            when(userRepository.findById(UserId.VALUE_1.getValue())).thenReturn(Optional.of(user));
           userService.update(user, UserId.VALUE_1.getValue());
            verify(userRepository, times(1)).save(user);

        }

        @Test
        void shouldDeleteUserWhenUserIsValid (User user) {
            when(userRepository.findById(UserId.VALUE_1.getValue())).thenReturn(Optional.of(user));
            when(accountService.delete(UserId.VALUE_1.getValue())).thenReturn(true);
            userService.delete(UserId.VALUE_1.getValue());
            verify(userRepository, times(1)).deleteById(UserId.VALUE_1.getValue());
        }
    }

    @Nested
    @ExtendWith(MockitoExtension.class)
    public class InvalidData {

        @InjectMocks
        private UserApiService userService;

        @Mock
        private UserRepository userRepository;

        @Test
        void shouldReadUserWhenUserInvalid() {
            when(userRepository.findById(UserId.VALUE_1.getValue())).thenReturn(Optional.ofNullable(null));
            assertThrows(IllegalArgumentException.class, () -> userService.read(UserId.VALUE_1.getValue()));
        }
    }
}


