package pl.mateusz.rodak.zakladybukm.dao;

import pl.mateusz.rodak.zakladybukm.model.Druzyna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DruzynaDAO extends AbstractDAO {

    public DruzynaDAO(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public DruzynaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public Druzyna get(int id) throws SQLException {
        Druzyna druzyna = null;
        String sql = "SELECT * FROM DRUZYNA WHERE ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String nazwa = resultSet.getString("NAZWA_DRUZYNY");
            druzyna = new Druzyna(id, nazwa);
        }

        resultSet.close();
        statement.close();

        return druzyna;
    }


}
