package com.example.demo.web;

import com.example.demo.models.bindings.UserLoginBindingModel;
import com.example.demo.models.serviceModels.UserServiceModel;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserLogInController {
    private final UserService userService;

    public UserLogInController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String registerUser(Model model) {
        if (!model.containsAttribute("userLoginModel")) {
            model.addAttribute("userLoginModel", new UserLoginBindingModel());
            model.addAttribute("isExisting", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute UserLoginBindingModel userLoginModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginModel", userLoginModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginModel",
                    bindingResult);
            return "redirect:login";
        }
        UserServiceModel userServiceModel = userService.findUser(userLoginModel.getUsername());
        if (userServiceModel != null) {
            userService.login(userServiceModel);
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("userLoginModel", userLoginModel);
        redirectAttributes.addFlashAttribute("isExisting", true);
        return "redirect:login";
    }
    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }

}
