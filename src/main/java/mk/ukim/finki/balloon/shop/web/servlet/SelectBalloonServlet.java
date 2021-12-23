package mk.ukim.finki.balloon.shop.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "select-balloon-servlet", urlPatterns = "/selectBalloon")
public class SelectBalloonServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public SelectBalloonServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        springTemplateEngine.process("selectBalloonSize.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String size = req.getParameter("size");
        if (size == null || size.isEmpty()) {
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("hasError", true);
            context.setVariable("error", "Choose a size before submitting!");
            springTemplateEngine.process("selectBalloonSize.html", context, resp.getWriter());
            return;
        }
        req.getSession().setAttribute("size", size);
        resp.sendRedirect("/BalloonOrder.do");
    }

}
