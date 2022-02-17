package pl.mateusz.rodak.zakladybukm.controller;

import pl.mateusz.rodak.zakladybukm.dao.ListaMeczyDAO;
import pl.mateusz.rodak.zakladybukm.model.Mecz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListaMeczyControllerServlet extends AbstractControllerServlet {

    private ListaMeczyDAO listaMeczyDAO;

    public void setConnectionProperies() {

        listaMeczyDAO = new ListaMeczyDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void wykonaj(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        List<Mecz> listaMeczy = listaMeczyDAO.pokazWszystkieMecze();
        request.setAttribute("listaMeczy", listaMeczy);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaMeczy.jsp");
        dispatcher.forward(request, response);
    }


}
