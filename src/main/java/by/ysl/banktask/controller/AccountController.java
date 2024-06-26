package by.ysl.banktask.controller;

import by.ysl.banktask.entity.AccountOperation;
import by.ysl.banktask.service.account.AccountService;
import by.ysl.banktask.service.accountOperation.AccountOperationService;
import by.ysl.banktask.service.transactionAccount.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final AccountOperationService accountOperationService;

    @PostMapping("/{id}/deposit")
    @ResponseStatus(HttpStatus.OK)
    public boolean deposit(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return transactionService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    @ResponseStatus(HttpStatus.OK)
    public  boolean withdraw(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return transactionService.withdraw(id, amount);
    }

    @GetMapping("/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalance(@PathVariable Long id) {
        return accountService.getBalance(id);
    }

    @GetMapping("/{id}/all_operation")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountOperation> getAllOperation (@PathVariable Long id) {
        return accountOperationService.getAllOperation(id);
    }


}
