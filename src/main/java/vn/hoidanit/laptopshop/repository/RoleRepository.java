package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import vn.hoidanit.laptopshop.domain.Role;

@Controller
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}