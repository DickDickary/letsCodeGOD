package com.example.keyMy.repos;

import com.example.keyMy.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository<UserAccount,Long> {
    UserAccount findByUsername(String username);

    UserAccount findByActivationCode(String code);
}
