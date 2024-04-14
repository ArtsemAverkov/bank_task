package by.ysl.banktask.service.user;

import by.ysl.banktask.entity.User;

import java.util.List;

public interface UserService {
    User read(Long id);
    long create(User user);
    boolean update (User user, Long id);
    boolean delete (Long id);
    List<User> readAll ();
}
