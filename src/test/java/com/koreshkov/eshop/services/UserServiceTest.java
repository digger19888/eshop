package com.koreshkov.eshop.services;

import com.koreshkov.eshop.models.User;
import com.koreshkov.eshop.models.enums.Role;
import com.koreshkov.eshop.repositories.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void createUser_shouldCallRepository() {
        User user = new User();
        user.setEmail("test1@gmail.com");

        boolean isUserCreated = userService.createUser(user);

        Assert.assertTrue(isUserCreated);
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.ROLE_USER)));

        Mockito.verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
    }

    @Test
    public void createUserFail_shouldCallRepository() {
        User user = new User();
        user.setEmail("test2@gmail.com");
        Mockito.doReturn(new User())
                .when(userRepository)
                .findByEmail("test2@gmail.com");

        boolean isUserCreated = userService.createUser(user);

        Assert.assertFalse(isUserCreated);

        Mockito.verify(userRepository, Mockito.times(0)).save(user);
    }
}
