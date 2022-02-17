package pl.mateusz.rodak.zakladybukm.controller;

import pl.mateusz.rodak.zakladybukm.dao.ListaZakladowDAO;
import pl.mateusz.rodak.zakladybukm.utils.Utility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends AbstractControllerServlet {

    private ListaZakladowDAO listaZakladowDAO;

    @Override
    protected void setConnectionProperies() {

        listaZakladowDAO = new ListaZakladowDAO(jdbcURL, jdbcUsername, jdbcPassword);

    }

    @Override
    protected void wykonaj(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String pin = request.getParameter("pin");
        String wiadomosc = "";

        if (Utility.pinOrNameIsWrong(name, pin)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {
            String pinUzytkownika = listaZakladowDAO.sprawdzPseudonim(name);
            HttpSession session = request.getSession();

            if (Utility.comparePins(pinUzytkownika, pin)) {

                session.setAttribute("name", name);
                session.setAttribute("pin", pin);

                response.sendRedirect(request.getContextPath() + "/listaMeczy");
            } else {
                wiadomosc = "UZYTKOWNIK O TEJ NAZWIE UZYWA INNEGO PINU!";
                request.setAttribute("wiadomoscZwrotna", wiadomosc);

                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        }
    }


}


