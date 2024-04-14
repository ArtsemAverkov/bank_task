package by.ysl.banktask.common.integration;


import by.ysl.banktask.common.extension.ValidParameterResolverAccountOperation;
import by.ysl.banktask.common.utill.UserId;
import by.ysl.banktask.entity.AccountOperation;
import by.ysl.banktask.repository.AccountOperationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

/**
 Integration tests for the NewsPostgresQLRepository class.
 */
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(ValidParameterResolverAccountOperation.class)
@DisplayName("Account Operation Repository Test")
public class AccountOperationRepositoryPostgresQLRepositoryTest extends TestContainerInitializer {

    @Autowired
    private AccountOperationRepository accountOperationRepository;

    @Autowired
    private  TestEntityManager testEntityManager;

    @Test
    void shouldSearchNewsHasParameterQuery(){
        List<AccountOperation> list = accountOperationRepository.findAllByAccountId(UserId.VALUE_1.getValue());
        testEntityManager.flush();
        testEntityManager.getEntityManager().getTransaction().commit();
        Assertions
                .assertThat(list)
                .hasSize(2);
    }
}
