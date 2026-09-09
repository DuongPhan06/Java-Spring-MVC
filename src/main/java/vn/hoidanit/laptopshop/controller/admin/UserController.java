package vn.hoidanit.laptopshop.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    // Lay thong tin user
    @RequestMapping("admin/user")
    public String getuserPage(Model model, @RequestParam("page") Optional<String> PageOptional) {
        int page = 1;
        try {
             if (PageOptional.isPresent()) {
            page = Integer.parseInt(PageOptional.get());
            }
            else {
            // Page = 1
            }
        }
        catch (Exception e) {
            // Page = 1
        }
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<User> pages = this.userService.getfullUsers(pageable);
        List<User> users = pages.getContent();
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("users1", users);
        return "admin/user/show";
    }

    @RequestMapping("admin/user/{id}")
    public String getuserdetailPage(Model model, @PathVariable Long id) {
        model.addAttribute("id", id);
        User user = this.userService.getfullUserbyId(id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    // Update user
    @GetMapping("/admin/user/update/{id}")
    public String getupdateUserPage(Model model, @PathVariable Long id) {
        User user = this.userService.getfullUserbyId(id);
        model.addAttribute("newUser", user);// Lay user tu controller sang jsp
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update/{id}")
    public String postupdateUser(Model model, @ModelAttribute("newUser") User duongptit) {
        User currentUser = this.userService.getfullUserbyId(duongptit.getId());
        if (currentUser != null) {
            currentUser.setAddress(duongptit.getAddress());
            currentUser.setFullName(duongptit.getFullName());
            currentUser.setPhone(duongptit.getPhone());
            currentUser.setRole(duongptit.getRole());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    // Create user
    @GetMapping("/admin/user/create")
    public String getcreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String createUserPage(Model model,
            @ModelAttribute("newUser") @Valid User duongptit,
            BindingResult newUserbBindingResult,
            @RequestParam("file") MultipartFile file) {

        List<FieldError> errors = newUserbBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>>" + error.getField() + " - " + error.getDefaultMessage());
        }

        if (newUserbBindingResult.hasErrors()) {
            return "admin/user/create";
        }
        
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(duongptit.getPassword());
        duongptit.setAvartar(avatar);
        duongptit.setPassword(hashPassword);
        this.userService.handleSaveUser(duongptit);
        return "redirect:/admin/user";
    }

    // Delete a user
    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete/{id}")
    public String postDeleteUserPage(Model model, @ModelAttribute("newUser") User user) {
        this.userService.deleteUser(user.getId());
        return "redirect:/admin/user";
    }

}
