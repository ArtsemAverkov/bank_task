package by.ysl.banktask.common.controller;

import by.ysl.banktask.BankTaskApplication;
import by.ysl.banktask.common.extension.ValidParameterResolverUser;
import by.ysl.banktask.common.utill.UserId;
import by.ysl.banktask.controller.UserController;
import by.ysl.banktask.entity.User;
import by.ysl.banktask.service.account.AccountService;
import by.ysl.banktask.service.user.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(classes = BankTaskApplication.class)
@WebMvcTest(UserController.class)
@ExtendWith(ValidParameterResolverUser.class)
@AutoConfigureMockMvc
@DisplayName("Testing User Controller")
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void read(User user) throws Exception {
        Mockito.when(userService.read(UserId.VALUE_1.getValue())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", UserId.VALUE_1.getValue()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(user.getName())));
        Mockito.verify(userService).read(UserId.VALUE_1.getValue());
    }

    @Test
    public void create(User user) throws Exception {
        Mockito.when(userService.create(any(User.class))).thenReturn(UserId.VALUE_1.getValue());
        Mockito.when(accountService.create(any(User.class))).thenReturn(UserId.VALUE_1.getValue());
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"name\": \""+user.getName()+"\"\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(String.valueOf(UserId.VALUE_1.getValue())));
        Mockito.verify(userService).create(any(User.class));
    }

    @Test
    public void update(User user) throws Exception {
        Mockito.when(userService.read(UserId.VALUE_1.getValue())).thenReturn(user);
        Mockito.when(userService.update(user, UserId.VALUE_1.getValue())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.patch("/user/{id}", UserId.VALUE_1.getValue())
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"name\": \""+user.getName()+"\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        Mockito.verify(userService).update(any(User.class), Mockito.anyLong());
    }

    @Test
    public void delete(User user) throws Exception {
        Mockito.when(userService.read(UserId.VALUE_1.getValue())).thenReturn(user);
        Mockito.when(accountService.delete(UserId.VALUE_1.getValue())).thenReturn(true);
        Mockito.when(userService.delete(UserId.VALUE_1.getValue())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}", UserId.VALUE_1.getValue()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        Mockito.verify(userService).delete(UserId.VALUE_1.getValue());
    }

    @Test
    public void readAll(User user) throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Mockito.when(userService.readAll(Pageable.ofSize(10).withPage(0))).thenReturn(userList);
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]name", Matchers.is(user.getName())));
        Mockito.verify(userService).readAll(Pageable.ofSize(10).withPage(0));
    }
}
