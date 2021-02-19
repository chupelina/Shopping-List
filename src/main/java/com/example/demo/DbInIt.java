package com.example.demo;

import com.example.demo.models.entities.CategoryEntity;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbInIt implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    public DbInIt(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(categoryRepository.count()==0){
            addingCategories();
        }

    }

    private void addingCategories() {
        CategoryEntity food= new CategoryEntity();
        food.setName("Food");
        CategoryEntity drink= new CategoryEntity();
        drink.setName("Drink");
        CategoryEntity household= new CategoryEntity();
        household.setName("Household");
        CategoryEntity other= new CategoryEntity();
        other.setName("Other");
        categoryRepository.saveAll(List.of(food,drink,household,other));
    }
}
