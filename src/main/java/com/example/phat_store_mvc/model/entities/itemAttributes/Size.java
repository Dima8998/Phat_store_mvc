package com.example.phat_store_mvc.model.entities.itemAttributes;

import lombok.Getter;

import java.util.List;

public enum Size {
    HATS(List.of("S", "M", "L")),
    CLOTHES(List.of("XS", "S", "M", "L", "XL")),
    SHOES(List.of("36", "37", "38", "39", "40", "41", "42", "43", "44"));

    @Getter
    private final List<String> sizes;

    Size(List<String> sizes) {
        this.sizes = sizes;
    }

}