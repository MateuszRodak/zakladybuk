package pl.mateusz.rodak.zakladybukm.controller;

import pl.mateusz.rodak.zakladybukm.dao.ListaMeczyDAO;
import pl.mateusz.rodak.zakladybukm.dao.ListaZakladowDAO;
import pl.mateusz.rodak.zakladybukm.model.Mecz;
import pl.mateusz.rodak.zakladybukm.utils.Utility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ZakladControllerServlet extends AbstractControllerServlet {

    private ListaMeczyDAO listaMeczyDAO;
    private ListaZakladowDAO listaZakladowDAO;

    @Override
    public void setConnectionProperies() {
        listaMeczyDAO = new ListaMeczyDAO(jdbcURL, jdbcUsername, jdbcPassword);
        listaZakladowDAO = new ListaZakladowDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void wykonaj(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = (String) session.getAttribute("name");
        String bet1 = (String) session.getAttribute("bet1");
        String bet2 = (String) session.getAttribute("bet2");
        String pin = (String) session.getAttribute("pin");

        RequestDispatcher dispatcher;

        if (Utility.pinOrNameIsWrong(name, pin)) {
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {

            int liczbaZakladow = listaZakladowDAO.liczbaZakladow(id, name, pin);
            request.setAttribute("liczbaZakladow", liczbaZakladow);

            Mecz mecz = listaMeczyDAO.get(id);
            request.setAttribute("wybranyMecz", mecz);

            if (mecz.getId() > 0) {

                boolean bet = listaZakladowDAO.getBet(id, name, pin, bet1, bet2);
                request.setAttribute("BET", bet);

                dispatcher = request.getRequestDispatcher("wprowadzenieZakladu.jsp?id=" + id);
                dispatcher.forward(request, response);

                session.setAttribute("bet1", null);
                session.setAttribute("bet2", null);

            } else {
                dispatcher = request.getRequestDispatcher("listaMeczy.jsp");
                dispatcher.forward(request, response);
            }
        }


    }

}
