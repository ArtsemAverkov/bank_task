package by.ysl.banktask.controller;

import by.ysl.banktask.entity.User;
import by.ysl.banktask.service.user.UserApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserApiService userService;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User read(@PathVariable @Valid Long id) {
        return userService.read(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long create(@RequestBody @Valid User user){
        return userService.create(user);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable @Valid Long id, @RequestBody @Valid User user){
         userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @Valid Long id){
        userService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> readAll() {
        return userService.readAll();
    }
}
