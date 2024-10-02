package com.example.phat_store_mvc.model.security;

import com.example.phat_store_mvc.model.BaseEntity;
import com.example.phat_store_mvc.model.entities.shop.Order;
import com.example.phat_store_mvc.model.entities.shop.Profile;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashSet;

@Getter
@Setter
@Builder
@Entity
@Table(name = "application_user_t")
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser extends BaseEntity {
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Profile profile;

    public ApplicationUser(String email, String password) {
        this.username = "";
        this.password = password;
        this.role = role.ROLE_USER;
        this.profile = Profile.builder()
                .user(this)
                .email(email)
                .orders(new HashSet<>(){{
                    add(Order.builder()
                            .isPaid(false)
                            .positions(new HashSet<>())
                            .build());
                }})
                .build();
    }

    public UserDetails securityUserFromEntity() {
        return new User(
                this.profile.getEmail(),
                password,
                true,
                true,
                true,
                true,
                new ArrayList<>(){{add(role);}}
        );
    }
}
