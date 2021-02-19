package com.example.demo.services.servicesImpl;

import com.example.demo.models.entities.CategoryEntity;
import com.example.demo.models.entities.ProductEntity;
import com.example.demo.models.serviceModels.ProductBying;
import com.example.demo.models.serviceModels.ProductServiceModel;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;


    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;

    }

    @Override
    public void addProduct(ProductServiceModel product) {
        ProductEntity productEntity= modelMapper.map(product, ProductEntity.class);
        productEntity.setCategory(categoryRepository.findByName(product.getCategory()));
        productRepository.save(productEntity);
    }

    @Override
    public void buyCurrentProduct(Long id) {
       productRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice() {
        return productRepository.findTotalSum();
    }

    @Override
    public List<ProductBying> findAllByCategory(String category) {
        List<ProductBying> current =
                productRepository.findAllByCategory(categoryRepository.findByName(category))
                .stream().map(c->modelMapper.map(c, ProductBying.class)).collect(Collectors.toList());
        return current;
    }

    @Override
    public void buyAllProducts() {
        productRepository.deleteAll();
    }
}
