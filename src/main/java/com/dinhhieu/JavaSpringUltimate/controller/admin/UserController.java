package com.dinhhieu.JavaSpringUltimate.controller.admin;

import com.dinhhieu.JavaSpringUltimate.domain.User;
import com.dinhhieu.JavaSpringUltimate.service.UploadService;
import com.dinhhieu.JavaSpringUltimate.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {
    private UserService userService;
    private final UploadService uploadService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService,
                          UploadService uploadService,
                         PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.uploadService = uploadService;

        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model){
        List<User> listUsers = userService.getAllUser();
        model.addAttribute("users",listUsers);
        return "admin/user/view";
    }

    @RequestMapping("/admin/user/create")
    public String getcreatUserPage(Model model){
        model.addAttribute("newUser",new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model,
                                 @ModelAttribute("newUser") @Valid  User hieu,
                                 BindingResult newUserBindingResult,
                                 @RequestParam("avatarFile") MultipartFile file
                                 ) throws IOException {

        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors){
            System.out.println(error.getField()+" - "+ error.getDefaultMessage());
        }

        //validate
        if(newUserBindingResult.hasErrors()){
            return "/admin/user/create";
        }

        String avatar = this.uploadService.handleSaveUploadFile(file,"avatar");
        String hashPassword = this.passwordEncoder.encode(hieu.getPassword());

        hieu.setAvatar(avatar);
        hieu.setPassword(hashPassword);
        hieu.setRole(this.userService.getRoleByName(hieu.getRole().getName()));
        this.userService.handleSaveUser(hieu);

        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
    public String getDetailUser(Model model, @PathVariable long id){
         Optional<User> user = userService.getUserById(id);
        System.out.println(user);
//        model.addAttribute("detailUser",user);
//        model.addAttribute("newUser",new User());
//        return "admin/user/create";
//        model.addAttribute("id",id);
        model.addAttribute("user",user.get());
        return "admin/user/viewDetail";
    }

    @RequestMapping(value = "/admin/user/update/{id}", method = RequestMethod.GET)
    public String getupdateUser(Model model, @PathVariable long id){
        Optional<User> currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser",currentUser);
        return "admin/user/update";
    }
    @RequestMapping(value = "/admin/user/update", method = RequestMethod.POST)
    public String postupdateUser(Model model, @ModelAttribute("newUser") User user){
        Optional<User> currentUser = this.userService.getUserById(user.getId());
        if(currentUser.isPresent()){
            currentUser.get().setAddress(user.getAddress());
            currentUser.get().setFullName(user.getFullName());
            currentUser.get().setPhone(user.getPhone());
            this.userService.handleSaveUser(currentUser.get());
        }
        return "redirect:/admin/user";
    }

    @DeleteMapping(value = "/admin/user/{id}")
    public String deleteUser(@ModelAttribute("deleteUser") @PathVariable long id){
        this.userService.deleteUserById(id);
        return "redirect:admin/user";
    }
}
