package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.validation.BindingResult;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    ProductController(UploadService uploadService, ProductService productService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getDashboard(
            Model model,
            @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            } else {
                // Page = 1
            }
        } catch (Exception e) {
            // Page = 1
        }
        Pageable pageable = PageRequest.of(page - 1, 5);

        Page<Product> prs = productService.fetchProduct(pageable);
        List<Product> listProducts = prs.getContent();

        model.addAttribute("products", listProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", prs.getTotalPages());

        return "admin/product/show";
    }

    // Create a Product
    @GetMapping("/admin/product/create1")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create1";
    }

    @PostMapping("/admin/product/create1")
    public String postCreateProductPage(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("file") MultipartFile file) {
        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>>" + error.getField() + " - " +
                    error.getDefaultMessage());
        }
        if (newProductBindingResult.hasErrors()) {
            return "admin/product/create1";
        }
        String images = this.uploadService.handleSaveUploadFile(file, "product");
        product.setImage(images);
        this.productService.createProduct(product);
        return "redirect:/admin/product";
    }

    // Detail a Product
    @GetMapping("admin/product/{id}")
    public String getproductdetailPage(Model model, @PathVariable Long id) {
        model.addAttribute("id", id);
        Product product = this.productService.detailProduct(id);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    // Update the product
    @GetMapping("admin/product/update/{id}")
    public String getproductupdatePage(Model model, @PathVariable Long id) {
        Product product = this.productService.detailProduct(id);
        model.addAttribute("newProduct", product);
        return "admin/product/update";
    }

    @PostMapping("admin/product/update/{id}")
    public String postproductupdatePage(Model model, @PathVariable Long id,
            @ModelAttribute("newProduct") Product product,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file) {

        // validate
        if (bindingResult.hasErrors()) {
            return "admin/product/update";
        }
        Product currentProduct = this.productService.detailProduct(id);
        if (currentProduct != null) {
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setDetailDesc(product.getDetailDesc());
            currentProduct.setShortDesc(product.getShortDesc());
            currentProduct.setQuantity(product.getQuantity());
            currentProduct.setFactory(product.getFactory());
            if (file != null && !file.isEmpty()) {
                String image = this.uploadService.handleSaveUploadFile(file, "product");
                currentProduct.setImage(image);
            }
            this.productService.createProduct(currentProduct);
        }
        return "redirect:/admin/product";
    }

    // Delete the Product
    @GetMapping("admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable Long id) {
        model.addAttribute("id", id);
        model.addAttribute("newProduct", new Product());
        return "admin/product/delete";
    }

    @PostMapping("admin/product/delete/{id}")
    public String postDeleteProductPage(Model model, @PathVariable Long id) {
        model.addAttribute("id", id);
        this.productService.deleteProduct(id);
        return "redirect:/admin/product";
    }

}
