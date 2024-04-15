package by.ysl.banktask.common.extension;

import by.ysl.banktask.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class ValidParameterResolverUser implements ParameterResolver {

    private final List<User> userList = Arrays.asList(
    new User(new Random().nextLong(), "Anton"),
    new User(new Random().nextLong(), "Artem"));

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == User.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return userList.get(new Random().nextInt(userList.size()));
    }
}

