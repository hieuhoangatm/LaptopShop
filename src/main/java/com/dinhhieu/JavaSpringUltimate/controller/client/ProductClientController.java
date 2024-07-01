package com.dinhhieu.JavaSpringUltimate.controller.client;

import com.dinhhieu.JavaSpringUltimate.domain.Product;
import com.dinhhieu.JavaSpringUltimate.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProductClientController {
    private final ProductService productService;

    public ProductClientController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getDetailProductItem(Model model,@PathVariable long id){
        Optional<Product> product = this.productService.getProductById(id);
        model.addAttribute("product",product.get());
        return "client/product/detail";
    }


}
