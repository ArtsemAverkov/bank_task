package by.ysl.banktask.service.user;

import by.ysl.banktask.entity.User;
import by.ysl.banktask.repository.UserRepository;
import by.ysl.banktask.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;
import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserApiService implements UserService{

    private final UserRepository userRepository;
    private final AccountService accountService;


    @Override
    public User read(Long id) {
        return userRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Invalid User id:" + id));
    }

    @Override
    public long create(User user) {
        User save = userRepository.save(user);
        accountService.create(user);
        return save.getId();
    }

    @Override
    @Transient
    public boolean update(User user, Long id) {
        read(id);
        user.setId(id);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        read(id);
        accountService.delete(id);
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public List<User> readAll(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }
}
