package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.User;

// import org.springframework.data.jpa.repository.JpaRepository;

// import vn.hoidanit.laptopshop.domain.User;
// import java.util.List;

// public interface UserRepository extends JpaRepository<User, Long> {
//   User save(User newUser);
//   List<User> findByEmail(String email);
//   // find by email and address
//   List<User> findByEmailAndAddress(String email, String address);

// }
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User save(User ptit);

  List<User> findAllByEmail(String email);

  boolean existsByEmail(String email);

  User findByEmail(String email);

}
