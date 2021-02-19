package com.example.demo.web;

import com.example.demo.models.bindings.ProductAddingBindingModel;
import com.example.demo.models.serviceModels.ProductServiceModel;
import com.example.demo.secutiry.CurrentUser;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CurrentUser currentUser;

    public ProductsController(ModelMapper modelMapper, ProductService productService, CategoryService categoryService, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.categoryService = categoryService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        if(currentUser.getId()==null){
            return "index";
        }
        if(!model.containsAttribute("productModel")){
            model.addAttribute("productModel", new ProductAddingBindingModel());
            model.addAttribute("categories", categoryService.getAllCategories() );
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String confirmAdding(@Valid @ModelAttribute ProductAddingBindingModel productModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() ){

            redirectAttributes.addFlashAttribute("productModel", productModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("categories", categoryService.getAllCategories());
            return "redirect:add";
        }
        ProductServiceModel product=modelMapper.map(productModel, ProductServiceModel.class);

        productService.addProduct(product);
        return "redirect:/";
    }
    @GetMapping("/buy/{id}")
    public String buyProduct(@PathVariable String id){
        productService.buyCurrentProduct(Long.parseLong(id));
        return "redirect:/";
    }
    @GetMapping("/buy/all")
    public String buyProduct(){
        productService.buyAllProducts();
        return "redirect:/";
    }





}
