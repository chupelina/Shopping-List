package com.example.demo.web;

import com.example.demo.models.serviceModels.ProductBying;
import com.example.demo.secutiry.CurrentUser;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ProductService productService;

    public HomeController(CurrentUser currentUser, ProductService productService) {
        this.currentUser = currentUser;

        this.productService = productService;
    }

    @GetMapping("/")
    public String homePage(Model model){
        if(currentUser.getId()==null){
            return "index";
        }
      model.addAttribute("totalSum", productService.getTotalPrice()==null? 0: productService.getTotalPrice());
        model.addAttribute("drinks", productService.findAllByCategory("Drink"));
        model.addAttribute("foods", productService.findAllByCategory("Food"));
        model.addAttribute("households", productService.findAllByCategory("Household"));
        model.addAttribute("others", productService.findAllByCategory("Other"));
        return "home";
    }

}
