package com.example.phat_store_mvc.model.entities.stock.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {
    private Integer id;
    private String name;
}
