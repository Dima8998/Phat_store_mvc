package com.example.phat_store_mvc.model.entities.shop;

import com.example.phat_store_mvc.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "order_t")
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {
    @Column(name = "isPaid")
    private Boolean isPaid;
    @ManyToOne
    private Profile profile;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Set<OrderPosition> positions;
}
