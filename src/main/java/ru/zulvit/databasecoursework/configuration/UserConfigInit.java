package ru.zulvit.databasecoursework.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.zulvit.databasecoursework.model.User;
import ru.zulvit.databasecoursework.service.UserService;

import java.time.LocalDateTime;

@Component
public class UserConfigInit implements ApplicationRunner {

    private final UserService userService;

    @Autowired
    public UserConfigInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userService.getAllUsers().isEmpty()) {
            User user = new User();
            user.setName("Default");
            user.setLastName("User");
            user.setSurname("Surname");
            user.setBirthday(LocalDateTime.now().minusYears(30));
            user.setEmail("defaultuser@example.com");
            user.setPassword("password123");
            user.setRegistrationDate(LocalDateTime.now());
            user.setLastLoginDate(LocalDateTime.now());

            userService.saveUser(user);
        }
    }
}
