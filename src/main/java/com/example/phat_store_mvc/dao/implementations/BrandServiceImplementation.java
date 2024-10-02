package com.example.phat_store_mvc.dao.implementations;

import com.example.phat_store_mvc.dao.services.BrandService;
import com.example.phat_store_mvc.model.entities.stock.entities.Brand;

import com.example.phat_store_mvc.repositories.BrandRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImplementation implements BrandService {
    private final BrandRepo repo;

    @Override
    public List<Brand> findAll() {
        return null;
    }

    @Override
    public Optional<Brand> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Brand save(Brand brand) {
        return repo.save(brand);
    }

    @Override
    public void deleteById(int id) {

    }
}
