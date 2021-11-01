package si.fri.prpo.skupina00.api.servlets;

import si.fri.prpo.skupina00.entitete.User;
import si.fri.prpo.skupina00.storitve.UserBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users")
public class UserServlet  extends HttpServlet {

    @Inject
    private UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userBean.getUsers();

        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.println("Users:");

        for (User u: users) {
            pw.println(u.getName() + " " + u.getSurname() + " <" + u.getEmail() + ">");
        }

        pw.close();
    }
}
