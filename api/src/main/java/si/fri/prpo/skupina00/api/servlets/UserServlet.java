package si.fri.prpo.skupina00.api.servlets;

import si.fri.prpo.skupina00.entitete.User;
import si.fri.prpo.skupina00.storitve.beans.UserBean;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(UserServlet.class.getName());

    @Inject
    private UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = userBean.getUsers();

        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.println("Users:");

        for (User u : users) {
            pw.println(u.toString());
        }

        pw.close();
    }
}
