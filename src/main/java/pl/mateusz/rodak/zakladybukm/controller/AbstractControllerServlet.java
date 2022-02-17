package pl.mateusz.rodak.zakladybukm.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public abstract class AbstractControllerServlet extends HttpServlet {


    protected String jdbcURL;
    protected String jdbcUsername;
    protected String jdbcPassword;


    public void init() {
        jdbcURL = getServletContext().getInitParameter("jdbcURL");
        jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        setConnectionProperies();

        try {
            wykonaj(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract void setConnectionProperies();

    protected abstract void wykonaj(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException;


}
