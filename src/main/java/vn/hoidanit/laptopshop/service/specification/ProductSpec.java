package vn.hoidanit.laptopshop.service.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;

public class ProductSpec {
        public static Specification<Product> nameLike(String name) {
                return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.NAME),
                                "%" + name + "%");
        }

        public static Specification<Product> factoryLike(String factory) {
                return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                                root.get(Product_.FACTORY),
                                factory);
        }

        public static Specification<Product> priceGreaterThanorEqual(double price) {
                return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
                                root.get(Product_.PRICE),
                                price);
        }

        public static Specification<Product> factoryLikes(String factory) {
                return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                                root.get(Product_.FACTORY), 
                                factory);
        }

        public static Specification<Product> matchListFactory(List<String> listFactory) {
                return (root, query, criteriaBuilder) -> root.get(Product_.FACTORY).in(listFactory);
        }

}
