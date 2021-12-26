package mk.ukim.finki.balloon.shop.web.controller;

import mk.ukim.finki.balloon.shop.model.Order;
import mk.ukim.finki.balloon.shop.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getOrderPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Order> orders = orderService.listOrders();
        model.addAttribute("orders", orders);
        return "userOrders";
    }

    @PostMapping("/select-color")
    public String addColor(@RequestParam String color, HttpServletRequest request) {
        if (color == null || color.isEmpty()) {
            // TODO: Select color exception
        }
        request.getSession().setAttribute("color", color);
        return "selectBalloonSize";
    }

    @PostMapping("/select-size")
    public String addSize(@RequestParam String size, HttpServletRequest request) {
        if (size == null || size.isEmpty()) {
            // TODO: Select size exception
        }
        request.getSession().setAttribute("size", size);
        return "selectBalloonDate";
    }

    @PostMapping("/select-date")
    public String addDate(@RequestParam("dateCreated") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateCreated, HttpServletRequest request) {
        request.getSession().setAttribute("dateCreated", dateCreated);
        return "confirmationInfo";
    }

    @PostMapping("/confirm")
    public String confirm(HttpServletRequest request, Model model) {
        String balloonColor = (String) request.getSession().getAttribute("color");
        String balloonSize = (String) request.getSession().getAttribute("size");
        LocalDateTime dateCreated = (LocalDateTime) request.getSession().getAttribute("dateCreated");
        orderService.placeOrder(balloonColor, balloonSize, dateCreated);
        return "redirect:/orders";
    }

    @GetMapping("/filter")
    public String filterByDate(@RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
                               @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo,
                               Model model) {
        List<Order> orders = orderService.findAllByFilterDate(dateFrom, dateTo);
        model.addAttribute("orders", orders);
        return "userOrders";
    }

}
