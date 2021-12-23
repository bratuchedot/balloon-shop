package mk.ukim.finki.balloon.shop.web;

import mk.ukim.finki.balloon.shop.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user-orders-servlet", urlPatterns = "/orders")
public class UserOrdersServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public UserOrdersServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("orders", orderService.listOrders());
        springTemplateEngine.process("userOrders.html", context, resp.getWriter());
    }

}
