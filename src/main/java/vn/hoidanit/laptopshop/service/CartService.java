package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    public final UserRepository userRepository;

    public CartService(CartRepository cartRepository, CartDetailRepository cartDetailRepository,
            UserRepository userRepository) {
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public List findCartDetail(Cart cart) {
        return this.cartDetailRepository.findByCart(cart);
    }

    public Cart findCartbyUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public void deleteCartDetail(long id) {
        CartDetail cartDetail = this.cartDetailRepository.findById(id).orElse(null);
        Cart cart = cartDetail.getCart();
        this.cartDetailRepository.delete(cartDetail);
        if (cart.getSum() > 1) {
            cart.setSum(cart.getSum() - 1);
            this.cartRepository.save(cart);
        } else {
            this.cartRepository.delete(cart);
        }
    }

    public void HanleUpdateCartbeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail x : cartDetails) {
            Optional<CartDetail> cdDetail = this.cartDetailRepository.findById(x.getId());
            if (cdDetail.isPresent()) {
                CartDetail currentCartDetail = cdDetail.get();
                currentCartDetail.setQuantity(currentCartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }
}
