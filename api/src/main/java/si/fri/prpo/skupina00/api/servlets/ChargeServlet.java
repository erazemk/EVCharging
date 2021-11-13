package si.fri.prpo.skupina00.api.servlets;

import si.fri.prpo.skupina00.entitete.Charge;
import si.fri.prpo.skupina00.storitve.beans.ChargeBean;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/charges")
public class ChargeServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ChargeServlet.class.getName());

    @Inject
    private ChargeBean chargeBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Charge> chargeList = chargeBean.getCharges();

        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.println("Charges:");

        for (Charge charge : chargeList) {
            pw.println(charge.toString());
        }

        pw.close();
    }
}
