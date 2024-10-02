package com.example.phat_store_mvc.model.entities.stock.entities;

import com.example.phat_store_mvc.model.BaseEntity;
import com.example.phat_store_mvc.model.entities.itemAttributes.Color;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "stock_position_t")
@AllArgsConstructor
@NoArgsConstructor
public class StockPosition extends BaseEntity {
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;
    @Column(name = "size")
    private String size;
    @ManyToOne
    private Item item;
}
