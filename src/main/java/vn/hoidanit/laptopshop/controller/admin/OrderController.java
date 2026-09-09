package vn.hoidanit.laptopshop.controller.admin;

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

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.hoidanit.laptopshop.service.OrderService;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderController(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository,
            OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderService = orderService;
    }

    @GetMapping("/admin/order")
    public String getDashboard(Model model, @RequestParam("page") Optional<String> OptionalPage) {
        int page = 1;
        try {
            if (OptionalPage.isPresent()) {
                page = Integer.parseInt(OptionalPage.get());
            } else {
                // Page = 1
            }
        } catch (Exception e) {
            // Page = 1
        }
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Order> pages = this.orderService.getAllOrders(pageable);
        List<Order> orders = pages.getContent();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("orders", orders);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getDetailOrder(Model model, @PathVariable long id) {
        List<OrderDetail> orderDetails = this.orderDetailRepository.findByOrderId(id);
        model.addAttribute("orderDetails", orderDetails);
        return "admin/order/detail";
    }
    

    @GetMapping("/admin/order/update/{id}")
    public String getMethodName(Model model, @PathVariable long id) {
        Optional<Order> od = this.orderRepository.findById(id);
        if (od.isPresent()) {
            Order order = od.get();
            model.addAttribute("singleOrder", order);
        }
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String postUpdateOrder(@ModelAttribute("singleOrder") Order order) {
        this.orderService.UpdateStatusOrder(order);
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getDeletePage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newOrder", new Order());
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String postMethodName(@ModelAttribute("newOrder") Order order) {
        this.orderService.DeleteOrder(order);
        return "redirect:/admin/order";
    }

}
