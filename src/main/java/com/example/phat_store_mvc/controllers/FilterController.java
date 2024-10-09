package com.example.phat_store_mvc.controllers;

import com.example.phat_store_mvc.model.entities.stock.entities.Item;
import com.example.phat_store_mvc.repositories.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/filter")
public class FilterController {
    private final ItemRepo itemRepo;
    @GetMapping
    public String filter(@RequestParam(required = false) int categoryId, Model model) {
        List<Item> items = itemRepo.filterByCategoryId(categoryId)
                .stream()
                .filter(item -> item.isInStock())
                .toList();
        model.addAttribute("items", items);
        return "/ui/pages/filterResult";
    }
}
