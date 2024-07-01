package com.dinhhieu.JavaSpringUltimate.controller.admin;

import com.dinhhieu.JavaSpringUltimate.domain.Product;
import com.dinhhieu.JavaSpringUltimate.service.ProductService;
import com.dinhhieu.JavaSpringUltimate.service.UploadService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class ProductController {
    private final ProductService productService;

    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getProduct(Model model){
        List<Product> listProducts = productService.getAllProduct();
        model.addAttribute("products",listProducts);
        return "admin/product/view";
    }

    @GetMapping("/admin/product/create")
    public String createProductPage(Model model){
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("admin/product/create")
    public String createProductPost(Model model,
                                 @ModelAttribute("newProduct") Product product,
                                 @RequestParam("avatarFile") MultipartFile file ) throws IOException {
        String avatar = this.uploadService.handleSaveUploadFile(file,"product");
        product.setAvatar(avatar);
        this.productService.handleSaveProduct(product);

        return "redirect:/admin/product";
    }

    @GetMapping("admin/product/{id}")
    public String getDetailProduct(Model model, @PathVariable long id){
        Optional<Product> product = productService.getProductById(id);
        model.addAttribute("product",product.get());
        return  "admin/product/detail";
    }

    @GetMapping("admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable long id){
        Optional<Product> product = productService.getProductById(id);
        model.addAttribute("newProduct",product);
        return "admin/product/update";
    }

    @PostMapping ("admin/product/update")
    public String updateProduct(Model model, @ModelAttribute("newProduct") Product product){
        Optional<Product> product1 = this.productService.getProductById(product.getId());
        if(product1.isPresent()){
            product1.get().setName(product.getName());
            product1.get().setPrice(product.getPrice());
            product1.get().setQuantity(product.getQuantity());
            product1.get().setTarget(product.getTarget());
            product1.get().setFactory(product.getFactory());
            this.productService.handleSaveProduct(product1.get());
        }
        return "redirect:/admin/product";
    }

    @DeleteMapping("admin/product/{id}")
    @ResponseBody
    public String deleteProduct(@PathVariable long id){
//        Optional<Product> product = this.productService.getProductById(id);
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
}
