package pl.mateusz.rodak.zakladybukm.dao;


import pl.mateusz.rodak.zakladybukm.model.Druzyna;
import pl.mateusz.rodak.zakladybukm.model.Mecz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaMeczyDAO extends AbstractDAO {

    public ListaMeczyDAO(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public ListaMeczyDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public List<Mecz> pokazWszystkieMecze() throws SQLException {

        List<Mecz> list;
        String sql = "SELECT * FROM MECZ ORDER BY DATA_ROZPOCZECIA DESC";

        connect();

        Statement statement = jdbcConnection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        list = stworzListe(resultSet);

        statement.close();
        disconnect();

        return list;
    }

    private List<Mecz> stworzListe(ResultSet resultSet) throws SQLException {

        List<Mecz> listaMeczow = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String miasto = resultSet.getString("MIASTO");
            Timestamp godzinaMeczu = resultSet.getTimestamp("DATA_ROZPOCZECIA");
            int druzyna1 = resultSet.getInt("DRUZYNA1");
            int druzyna2 = resultSet.getInt("DRUZYNA2");
          //  String druzyna1Wynik = resultSet.getString("DRUZYNA1_GOLEZ");
           // String druzyna2Wynik = resultSet.getString("DRUZYNA2_WYNIK");

            Mecz mecz = new Mecz(id, godzinaMeczu, miasto, druzyna1, druzyna2);
            listaMeczow.add(mecz);

            DruzynaDAO druzynaDAO = new DruzynaDAO(jdbcConnection);
            Druzyna druzyna1Obj = druzynaDAO.get(druzyna1);
            Druzyna druzyna2Obj = druzynaDAO.get(druzyna2);
            mecz.setDruzyna1Obj(druzyna1Obj);
            mecz.setDruzyna2Obj(druzyna2Obj);

        }

        resultSet.close();
        return listaMeczow;
    }

    public Mecz get(int id) throws SQLException {
        Mecz mecz = null;
        String sql = "SELECT * FROM MECZ WHERE ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String miasto = resultSet.getString("MIASTO");
            Timestamp godzinaMeczu = resultSet.getTimestamp("DATA_ROZPOCZECIA");
            int druzyna1 = resultSet.getInt("DRUZYNA1");
            int druzyna2 = resultSet.getInt("DRUZYNA2");
           // String druzyna1Wynik = resultSet.getString("DRUZYNA1_WYNIK");
           // String druzyna2Wynik = resultSet.getString("DRUZYNA2_WYNIK");

            mecz = new Mecz(id, godzinaMeczu, miasto, druzyna1, druzyna2);

            DruzynaDAO druzynaDAO = new DruzynaDAO(jdbcConnection);
            Druzyna druzyna1Obj = druzynaDAO.get(druzyna1);
            Druzyna druzyna2Obj = druzynaDAO.get(druzyna2);
            mecz.setDruzyna1Obj(druzyna1Obj);
            mecz.setDruzyna2Obj(druzyna2Obj);
        }

        resultSet.close();
        statement.close();

        return mecz;
    }

}