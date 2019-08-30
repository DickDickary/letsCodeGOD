package com.example.keyMy.service;


import com.example.keyMy.domain.Role;
import com.example.keyMy.domain.UserAccount;
import com.example.keyMy.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender sender;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(UserAccount user) {
        UserAccount userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {

            return false;

        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello %s! \n" +  "Take this link! http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            sender.send(user.getEmail(), "Activation code", message);
        }
        return true;
    }

    public boolean activatedUser(String code) {
        UserAccount userAccount = userRepo.findByActivationCode(code);

        if (userAccount == null){
           return false;
        }

        userAccount.setActivationCode(null);
        userRepo.save(userAccount);
        return true;
    }
}
