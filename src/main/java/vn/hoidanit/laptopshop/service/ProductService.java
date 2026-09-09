package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.service.specification.ProductSpec;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService, OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Product createProduct(Product pr) {
        return this.productRepository.save(pr);
    }


    public Page<Product> fetchProduct(org.springframework.data.domain.Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Page<Product> fetchProducts(org.springframework.data.domain.Pageable pageable, String name) {
        return this.productRepository.findAll(ProductSpec.nameLike(name), pageable);
    }

    public Page<Product> fetchProducts2(org.springframework.data.domain.Pageable pageable, String factory) {
        return this.productRepository.findAll(ProductSpec.factoryLike(factory), pageable);
    }

    public List<Product> FindAllProducts() {
        return this.productRepository.findAll();
    }

    public Product detailProduct(long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(long id) {
        Product product = this.productRepository.findById(id).orElse(null);
        if (product != null) {
            this.productRepository.deleteById(id);
        }
    }

    public void handleProducttoCart(String email, long product_id, HttpSession session) {
        User user = this.userService.getUserbyEmail(email);
        if (user != null) { // check user have cart ?
            Cart cart = this.cartRepository.findByUser(user);

            if (cart == null) {
                // Make new cart
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(0);

                cart = this.cartRepository.save(newCart);

            }

            // save cartdetail
            Optional<Product> productOptional = this.productRepository.findById(product_id);
            if (productOptional.isPresent()) {
                Product realProduct = productOptional.get();

                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
                if (oldDetail == null) {
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProduct(realProduct);
                    cartDetail.setPrice(realProduct.getPrice());
                    cartDetail.setQuantity(1);
                    this.cartDetailRepository.save(cartDetail);

                    // update cart sum
                    int s = cart.getSum() + 1;
                    cart.setSum(s);
                    cart = this.cartRepository.save(cart);
                    session.setAttribute("sum", s);

                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(oldDetail);
                }

            }
        }
    }

    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverPhone,
            String receiverAddress) {

        // step 1: Get cart by User
        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();
            if (cartDetails != null) {
                Order order = new Order();
                order.setUser(user);
                order.setReceiverAddress(receiverAddress);
                order.setReceiverName(receiverName);
                order.setReceiverPhone(receiverPhone);
                order.setStatus("PENDING");
                double sum = 0;
                for (CartDetail x : cartDetails) {
                    sum += (x.getPrice() * x.getQuantity());
                }
                order.setTotalPrice(sum);
                order = this.orderRepository.save(order);

                for (CartDetail x : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(x.getProduct());
                    orderDetail.setPrice(x.getPrice());
                    orderDetail.setQuantity(x.getQuantity());

                    this.orderDetailRepository.save(orderDetail);
                }

                for (CartDetail x : cartDetails) {
                    this.cartDetailRepository.deleteById(x.getId());
                }
            }
            this.cartRepository.deleteById(cart.getId());
            session.setAttribute("sum", 0);
        }
    }

}
