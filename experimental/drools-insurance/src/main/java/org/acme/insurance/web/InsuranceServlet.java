package org.acme.insurance.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class for Servlet: InsuranceServlet
 */
public class InsuranceServlet extends javax.servlet.http.HttpServlet implements
        javax.servlet.Servlet {
    static final long serialVersionUID = 1L;

    public InsuranceServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        DroolsBusiness business = new DroolsBusiness();
        try {
            business.execute(request);

            String redirectURL = "index.jsp";
            redirectURL += "?calculo=sim";
            redirectURL += "&aprovado=" + business.isApproved();
            redirectURL += "&fatorrisco=" + business.getRiskFactor();
            redirectURL += "&base=" + business.getBasePrice();
            redirectURL += "&valor=" + business.getInsurancePrice();
            redirectURL += "&estadoCivil=" + request.getParameter("estadoCivil");
            redirectURL += "&temFilhos=" + request.getParameter("temFilhos");
            redirectURL += "&sinistros=" + request.getParameter("sinistros");
            redirectURL += "&habilitacao=" + request.getParameter("habilitacao");
            redirectURL += "&garagemDia=" + request.getParameter("garagemDia");
            redirectURL += "&garagemNoite=" + request.getParameter("garagemNoite");
            redirectURL += "&profissao=" + request.getParameter("profissao");
            redirectURL += "&condicaoImovel=" + request.getParameter("condicaoImovel");
            redirectURL += "&assistencia24h=" + request.getParameter("assistencia24h");
            redirectURL += "&carroReserva=" + request.getParameter("carroReserva");
            redirectURL += "&vidros=" + request.getParameter("vidros");
            redirectURL += "&despExtra=" + request.getParameter("despExtra");
            redirectURL += "&alarme=" + request.getParameter("alarme");
            redirectURL += "&blindagem=" + request.getParameter("blindagem");
            redirectURL += "&sistemaSom=" + request.getParameter("sistemaSom");
            redirectURL += "&birthdate=" + request.getParameter("birthdate");

            response.sendRedirect(redirectURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}