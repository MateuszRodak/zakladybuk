package pl.mateusz.rodak.zakladybukm.controller;

import pl.mateusz.rodak.zakladybukm.dao.ListaZakladowDAO;
import pl.mateusz.rodak.zakladybukm.model.Zaklad;
import pl.mateusz.rodak.zakladybukm.utils.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SaveZakladControllerServlet extends AbstractControllerServlet {

    private ListaZakladowDAO listaZakladowDAO;

    @Override
    protected void setConnectionProperies() {
        listaZakladowDAO = new ListaZakladowDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void wykonaj(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        int meczId = Integer.parseInt(request.getParameter("meczId"));
        String bet1 = Utility.cleanInt(request.getParameter("bet1"));
        String bet2 = Utility.cleanInt(request.getParameter("bet2"));
        String name = request.getParameter("name");
        String pin = request.getParameter("pin");

        int liczbaZakladow = listaZakladowDAO.liczbaZakladow(meczId, name, pin);

        boolean bet = listaZakladowDAO.getBet(meczId, name, pin, bet1, bet2);

        request.setAttribute("BET", bet);

        HttpSession session = request.getSession();
        session.setAttribute("bet1", bet1);
        session.setAttribute("bet2", bet2);


        if (liczbaZakladow < 3 && !bet) {
            Zaklad zaklad = new Zaklad(meczId, name, pin, bet1, bet2);
            listaZakladowDAO.wstawZaklad(zaklad);
            response.sendRedirect(request.getContextPath() + "/przegladZakladow?id=" + meczId);
            session.setAttribute("bet1", null);
            session.setAttribute("bet2", null);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/Zaklad?id=" + meczId);
        }

    }
}
