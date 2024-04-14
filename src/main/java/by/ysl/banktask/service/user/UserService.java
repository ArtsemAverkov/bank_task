package by.ysl.banktask.service.user;

import by.ysl.banktask.entity.User;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {
    User read(Long id);
    long create(User user);
    boolean update (User user, Long id);
    boolean delete (Long id);
    List<User> readAll (Pageable pageable);
}
