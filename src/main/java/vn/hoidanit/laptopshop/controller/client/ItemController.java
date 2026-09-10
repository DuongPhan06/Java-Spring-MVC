package vn.hoidanit.laptopshop.controller.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.ProductCriteriaDTO;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.service.CartService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ItemController {
    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;
    private int ArrayList;
    private final CartDetailRepository cartDetailRepository;

    public ItemController(ProductService productService, UserService userService, CartService cartService,
            CartDetailRepository cartDetailRepository) {
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
        this.cartDetailRepository = cartDetailRepository;
    }

    @GetMapping("/product/{id}")
    public String getProductPage(Model model, @PathVariable Long id) {

        Product product = this.productService.detailProduct(id);
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProducttoCart(Model model, @PathVariable long id, HttpServletRequest request) {
        long product_id = id;
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");

        this.productService.handleProducttoCart(email, product_id, session);
        return "redirect:/";
    }

    // @GetMapping("/cart")
    // public String getCartPage(Model moldel) {
    // return "client/cart/show";
    // }

    @GetMapping("/cart")
    public String getCartPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User user = this.userService.getUserbyEmail(email);
        Cart cart = this.cartService.findCartbyUser(user);

        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();
        double totalPrice = 0;
        for (CartDetail x : cartDetails) {
            totalPrice += (x.getPrice() * x.getQuantity());
        }
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", cart);
        return "client/cart/show";
    }

    // Delete CartDetail
    @PostMapping("/delete-cart-product/{id}")
    public String postDeleteCartDetail(@PathVariable long id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        this.cartService.deleteCartDetail(id);
        Integer sum = (Integer) session.getAttribute("sum");
        if (sum > 1) {
            sum = sum - 1;
            session.setAttribute("sum", sum);
        } else {

            session.setAttribute("sum", 0);
        }
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String getCheckoutPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User user = this.userService.getUserbyEmail(email);
        Cart cart = this.cartService.findCartbyUser(user);
        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();
        double sum = 0;
        for (CartDetail x : cartDetails) {
            sum += (x.getPrice() * x.getQuantity());
        }
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", sum);
        return "client/cart/checkout";
    }

    @PostMapping("/confirm-checkout")
    public String postCheckOutPage(Model model, @ModelAttribute("cart") Cart cart) {
        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();
        this.cartService.HanleUpdateCartbeforeCheckout(cartDetails);
        return "redirect:/checkout";
    }

    @PostMapping("/place-order")
    public String postMethodName(HttpServletRequest request, @RequestBody String entity,
            @RequestParam("receiverName") String receiverName,
            @RequestParam("receiverAddress") String receiverAddress,
            @RequestParam("receiverPhone") String receiverPhone) {
        HttpSession session = request.getSession(false);
        User currentUser = new User();
        Long id = (Long) session.getAttribute("id");
        currentUser.setId(id);
        this.productService.handlePlaceOrder(currentUser, session, receiverName, receiverPhone, receiverAddress);
        return "redirect:/thanks";
    }

    @GetMapping("/thanks")
    public String getMethodName(Model model) {
        return "client/cart/thanks";
    }

    @GetMapping("/products")
    public String getDashboard(
            Model model, ProductCriteriaDTO productCriteriaDTO) {
        int page = 1;
        String factory = "";
        try {
            if (productCriteriaDTO.getPage().isPresent()) {
                page = Integer.parseInt(productCriteriaDTO.getPage().get());
            } else {
                // Page = 1
            }
        } catch (Exception e) {
            // Page = 1
        }

        Pageable pageable = PageRequest.of(page - 1, 5);

        Page<Product> prs = this.productService.fetchProduct(pageable);
        List<Product> listProducts = prs.getContent();

        model.addAttribute("products", listProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", prs.getTotalPages());

        return "client/product/show";
    }

}
