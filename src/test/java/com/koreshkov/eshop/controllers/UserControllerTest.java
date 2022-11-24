package com.koreshkov.eshop.controllers;

import com.koreshkov.eshop.models.User;
import com.koreshkov.eshop.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private User user;
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    @Test
    void userInfo() {
        final User user = mock(User.class);
        final Principal principal = mock(Principal.class);
        final Model model = mock(Model.class);
        when(userService.getUserByPrincipal(principal)).thenReturn(user);

        userController.userInfo(user,model, principal);

        assertNotNull(user);
    }
}
