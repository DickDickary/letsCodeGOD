package com.example.keyMy.controller;

import com.example.keyMy.domain.User;
import com.example.keyMy.domain.UserAccount;
import com.example.keyMy.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        return "greeting";
    }

    @GetMapping("/main")
    public String name(@RequestParam(required = false,defaultValue = "")String filter, Model model) {
        Iterable<User> users = userRepository.findAll();
        if (filter != null && !filter.isEmpty()) {
            users = userRepository.findByName(filter);
        } else users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("filter",filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal UserAccount account,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam Integer age,
            Map<String, Object> model) {
        User user = new User(name, email, age,account);

        userRepository.save(user);
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }


}


