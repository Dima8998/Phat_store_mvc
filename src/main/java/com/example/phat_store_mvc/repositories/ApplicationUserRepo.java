package com.example.phat_store_mvc.repositories;

import com.example.phat_store_mvc.model.security.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepo extends JpaRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findApplicationUserByProfileEmail(String email);
}
