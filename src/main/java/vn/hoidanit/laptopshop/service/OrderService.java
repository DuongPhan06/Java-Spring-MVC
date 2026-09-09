package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.controller.admin.OrderController;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public void UpdateStatusOrder(Order order) {
        Optional<Order> od = this.orderRepository.findById(order.getId());
        if (od.isPresent()) {
            Order currentOrder = od.get();
            currentOrder.setStatus(order.getStatus());
            this.orderRepository.save(currentOrder);
        }
    }

    public void DeleteOrder(Order order) {
        Optional<Order> od = this.orderRepository.findById(order.getId());
        if (od.isPresent()) {
            Order currentOrder = od.get();
            List<OrderDetail> orderDetails = currentOrder.getOrderDetails();
            for (OrderDetail x : orderDetails) {
                this.orderDetailRepository.deleteById(x.getId());
            }
            this.orderRepository.delete(currentOrder);
        }
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

}
