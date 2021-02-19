package com.example.demo.services.servicesImpl;

import com.example.demo.models.entities.CategoryEntity;
import com.example.demo.models.serviceModels.ProductBying;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<String> getAllCategories() {
        List<String> result = new ArrayList<>();
        categoryRepository.findAll().forEach(categoryEntity -> result.add(categoryEntity.getName()));
        return result;
    }


}
