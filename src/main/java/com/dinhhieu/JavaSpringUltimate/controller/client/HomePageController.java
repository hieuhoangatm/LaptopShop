package com.dinhhieu.JavaSpringUltimate.controller.client;

import com.dinhhieu.JavaSpringUltimate.DTO.RegisterDTO;
import com.dinhhieu.JavaSpringUltimate.domain.Product;
import com.dinhhieu.JavaSpringUltimate.domain.User;
import com.dinhhieu.JavaSpringUltimate.service.ProductService;
import com.dinhhieu.JavaSpringUltimate.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomePageController {
    private final ProductService productService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public HomePageController(ProductService productService, PasswordEncoder passwordEncoder, UserService userService) {
        this.productService = productService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @GetMapping("/")
    public String getHomePage(Model model){
        List<Product> product = this.productService.getAllProduct();
        model.addAttribute("products",product);
        return "client/homepage/view";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerUser") @Valid RegisterDTO registerDTO, BindingResult bindingResult){
//        List<FieldError> errors = bindingResult.getFieldErrors();
//        for(FieldError error : errors){
//            System.out.println(">>>"+ error.getField()+" - " + error.getDefaultMessage());
//        }
        if(bindingResult.hasErrors()){
            return "client/auth/register";
        }
        User user = this.userService.registerDTOtoUser(registerDTO);
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));
        this.userService.handleSaveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){

        return "client/auth/login";
    }
}
