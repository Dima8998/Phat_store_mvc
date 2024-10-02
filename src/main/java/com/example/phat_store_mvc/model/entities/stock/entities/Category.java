package com.example.phat_store_mvc.model.entities.stock.entities;

import com.example.phat_store_mvc.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "category_t")
@AllArgsConstructor
public class Category extends BaseEntity {
    @Column(name = "name")
    private String name;
    @OneToMany
    @JoinColumn(name = "category_id")
    private Set<Item> catalogue;

    public Category() {
        catalogue = new HashSet<>();
    }
}
