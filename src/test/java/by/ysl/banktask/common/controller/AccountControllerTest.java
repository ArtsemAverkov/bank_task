package by.ysl.banktask.common.controller;

import by.ysl.banktask.BankTaskApplication;
import by.ysl.banktask.common.extension.ValidParameterResolverAccount;
import by.ysl.banktask.common.extension.ValidParameterResolverAccountOperation;
import by.ysl.banktask.common.utill.UserId;
import by.ysl.banktask.controller.AccountController;
import by.ysl.banktask.entity.Account;
import by.ysl.banktask.entity.AccountOperation;
import by.ysl.banktask.repository.AccountRepository;
import by.ysl.banktask.service.account.AccountService;
import by.ysl.banktask.service.accountOperation.AccountOperationService;
import by.ysl.banktask.service.transactionAccount.TransactionService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = BankTaskApplication.class)
@WebMvcTest(AccountController.class)
@ExtendWith({ValidParameterResolverAccount.class, ValidParameterResolverAccountOperation.class})
@AutoConfigureMockMvc
@DisplayName("Testing Account Controller")
public class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    AccountOperationService accountOperationService;

    @MockBean
    AccountRepository accountRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deposit() throws Exception {
        BigDecimal bigDecimal = new BigDecimal("10.20");
        Mockito.when(transactionService.deposit(UserId.VALUE_1.getValue(), bigDecimal))
                .thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/account/{id}/deposit", UserId.VALUE_1.getValue())
                        .param("amount", bigDecimal.toString()))

                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        Mockito.verify(transactionService).deposit(UserId.VALUE_1.getValue(), bigDecimal);
    }

     @Test
    public void withdraw(Account account) throws Exception {
        BigDecimal bigDecimal = new BigDecimal("10.20");
         Mockito.when(accountRepository.findById(Mockito.anyLong()))
                 .thenReturn(Optional.ofNullable(account));
        Mockito.when(transactionService.deposit(UserId.VALUE_1.getValue(), bigDecimal))
                .thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/account/{id}/withdraw", UserId.VALUE_1.getValue())
                        .param("amount", bigDecimal.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        Mockito.verify(transactionService).withdraw(UserId.VALUE_1.getValue(), bigDecimal);
    }

    @Test
    public void getBalance() throws Exception {
        BigDecimal bigDecimal = new BigDecimal("10.20");
        Mockito.when(accountService.getBalance(UserId.VALUE_1.getValue())).thenReturn(bigDecimal);
        mockMvc.perform(MockMvcRequestBuilders.get("/account/{id}/balance", UserId.VALUE_1.getValue()))
                .andExpect(status().isOk())
                .andExpect(content().string(bigDecimal.toString()));
        Mockito.verify(accountService).getBalance(UserId.VALUE_1.getValue());
    }

    @Test
    public void getAllOperation(AccountOperation accountOperation) throws Exception {
        List<AccountOperation> accountOperationList = new ArrayList<>();
        accountOperationList.add(accountOperation);
        Mockito.when(accountOperationService.getAllOperation(UserId.VALUE_1.getValue())).thenReturn(accountOperationList);
        mockMvc.perform(MockMvcRequestBuilders.get("/account/{id}/all_operation", UserId.VALUE_1.getValue()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]id", Matchers.is(accountOperation.getId())));
        Mockito.verify(accountOperationService).getAllOperation(UserId.VALUE_1.getValue());
    }
}
