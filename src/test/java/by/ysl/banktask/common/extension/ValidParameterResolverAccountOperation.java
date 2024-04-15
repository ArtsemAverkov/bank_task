package by.ysl.banktask.common.extension;

import by.ysl.banktask.entity.Account;
import by.ysl.banktask.entity.AccountOperation;
import by.ysl.banktask.entity.User;
import by.ysl.banktask.utill.OperationType;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ValidParameterResolverAccountOperation implements ParameterResolver {

    private final List<AccountOperation> accountOperationList = Arrays.asList(
            new AccountOperation(new Random().nextLong(),
                    new Account(new Random().nextLong(),
                            new User(new Random().nextLong(),
                                    "name"),
                            new BigDecimal("80.55")),
                    OperationType.DEPOSIT,
                    new BigDecimal("100.55"),
                    LocalDateTime.now()));


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == AccountOperation.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return accountOperationList.get(new Random().nextInt(accountOperationList.size()));
    }
}