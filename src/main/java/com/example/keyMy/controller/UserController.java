package com.example.keyMy.controller;

import com.example.keyMy.domain.Role;
import com.example.keyMy.domain.UserAccount;
import com.example.keyMy.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")

@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users",userRepo.findAll());
        return "userList";
    }
    @GetMapping("{userAccount}")
    public String userEditForm(@PathVariable UserAccount userAccount, Model model){
        model.addAttribute("user", userAccount);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String,String> form,
            @RequestParam("userId") UserAccount account){

        account.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        account.getRoles().clear();

        for(String key : form.keySet()){
            if (roles.contains(key)){
                account.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(account);
        return "redirect:/user";
    }
}
