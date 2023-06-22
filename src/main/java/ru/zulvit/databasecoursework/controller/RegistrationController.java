package ru.zulvit.databasecoursework.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zulvit.databasecoursework.dto.UserDTO;
import ru.zulvit.databasecoursework.mapper.UserMapper;
import ru.zulvit.databasecoursework.model.User;
import ru.zulvit.databasecoursework.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String resister(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "register";
        }
        System.out.println(userDTO);
        userService.saveUser(UserMapper.INSTANCE.toUser(userDTO));
        return "redirect:/";
    }
}
