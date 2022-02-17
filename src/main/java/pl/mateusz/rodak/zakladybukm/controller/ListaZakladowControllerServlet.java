package pl.mateusz.rodak.zakladybukm.controller;


import pl.mateusz.rodak.zakladybukm.dao.ListaMeczyDAO;
import pl.mateusz.rodak.zakladybukm.dao.ListaZakladowDAO;
import pl.mateusz.rodak.zakladybukm.model.Mecz;
import pl.mateusz.rodak.zakladybukm.model.Zaklad;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListaZakladowControllerServlet extends AbstractControllerServlet {

    private ListaZakladowDAO listaZakladowDAO;
    private ListaMeczyDAO listaMeczyDAO;

    public void setConnectionProperies() {
        listaZakladowDAO = new ListaZakladowDAO(jdbcURL, jdbcUsername, jdbcPassword);
        listaMeczyDAO = new ListaMeczyDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void wykonaj(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        String pin = (String) session.getAttribute("pin");
        request.setAttribute("uzytkownikSesji", name);
        request.setAttribute("pinSesji", pin);

        List<Zaklad> listaZakladow = listaZakladowDAO.pokazWszystkieZaklady(id);
        request.setAttribute("listaZakladow", listaZakladow);

        Mecz mecz = listaMeczyDAO.get(id);
        request.setAttribute("wybranyMecz", mecz);

        int liczbaZakladow = listaZakladowDAO.liczbaZakladow(id, name, pin);
        request.setAttribute("liczbaZakladow", liczbaZakladow);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/przegladZakladow.jsp?id=" + id);
        dispatcher.forward(request, response);
    }


}
