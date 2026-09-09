package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import vn.hoidanit.laptopshop.domain.Role;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.repository.RoleRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    public final UserRepository userRepository;
    public final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public String hanldeHello() {
        return "Hello from service";
    }

    public Page<User> getfullUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public List<User> getfullUsersbymail(String email) {
        return this.userRepository.findAllByEmail(email);
    }

    public User getfullUserbyId(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User handleSaveUser(User user) {
        Role role = this.roleRepository.findByName(
                user.getRole().getName());

        user.setRole(role);

        return this.userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            this.userRepository.deleteById(id);
        }
    }

    // Mapper: Map thong tin tu DTO sang user
    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    
    public User setRoleUSER(User user) {
        Role role = this.roleRepository.findByName("USER");
        user.setRole(role);
        return user;
    }

    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUserbyEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public long countUsers() {
        return this.userRepository.count();
    }
    public long countProducts() {
        return this.productRepository.count();
    }
    public long countOrders() {
        return this.orderRepository.count();
    }
}