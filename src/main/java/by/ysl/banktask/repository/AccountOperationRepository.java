package by.ysl.banktask.repository;

import by.ysl.banktask.entity.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
    List<AccountOperation> findAllByAccountId(Long accountId);
}
