package com.example.phat_store_mvc.model.entities.shop;

import com.example.phat_store_mvc.model.BaseEntity;
import com.example.phat_store_mvc.model.entities.stock.entities.StockPosition;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "order_position_t")
@AllArgsConstructor
@NoArgsConstructor
public class OrderPosition extends BaseEntity {
    @Column(name = "amount")
    private Integer amount;
    @ManyToOne
    private Order order;
    @ManyToOne
    @JoinColumn(name = "stock_pos_id")
    private StockPosition stockPosition;

}
