package com.example.phat_store_mvc.dao.implementations;

import com.example.phat_store_mvc.dao.services.CategoryService;
import com.example.phat_store_mvc.model.entities.stock.entities.Category;
import com.example.phat_store_mvc.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {
    private final CategoryRepo repo;
    @Override
    public List<Category> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Category> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Category save(Category category) {
        return repo.save(category);
    }

    @Override
    public void deleteById(int id) {

    }
}
