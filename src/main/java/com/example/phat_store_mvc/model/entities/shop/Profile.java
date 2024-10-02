package com.example.phat_store_mvc.model.entities.shop;

import com.example.phat_store_mvc.model.BaseEntity;
import com.example.phat_store_mvc.model.security.ApplicationUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "profile_t")
@AllArgsConstructor
@NoArgsConstructor
public class Profile extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @OneToOne(mappedBy = "profile")
    private ApplicationUser user;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Set<Order> orders;

    public Profile(String email, ApplicationUser user) {
        this.name = "";
        this.email = email;
        this.user = user;
        this.orders = new HashSet<>();
    }
}
