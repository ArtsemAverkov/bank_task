package by.ysl.banktask.repository;

import by.ysl.banktask.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
