package pl.mateusz.rodak.zakladybukm.dao;

import pl.mateusz.rodak.zakladybukm.model.Mecz;
import pl.mateusz.rodak.zakladybukm.model.Zaklad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaZakladowDAO extends AbstractDAO {

    public ListaZakladowDAO(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public ListaZakladowDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public List<Zaklad> pokazWszystkieZaklady(int id) throws SQLException {

        String sql = "SELECT * FROM ZAKLAD WHERE MECZ_ID = ? ORDER BY DATA_ZAKLADU DESC";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        List<Zaklad> list = stworzListeZakladow(resultSet);

        resultSet.close();
        statement.close();

        disconnect();
        return list;
    }

    private List<Zaklad> stworzListeZakladow(ResultSet resultSet) throws SQLException {

        List<Zaklad> listaZakladow = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            int meczId = resultSet.getInt("MECZ_ID");
            String name = resultSet.getString("UZYTKOWNIK_NAME");
            String pin = resultSet.getString("UZYTKOWNIK_PIN");
            String druzyna1Gole = resultSet.getString("DRUZYNA1_GOLE");
            String druzyna2Gole = resultSet.getString("DRUZYNA2_GOLE");
            Timestamp dataZakladu = resultSet.getTimestamp("DATA_ZAKLADU");

            Zaklad zaklad = new Zaklad(id, meczId, name, pin, druzyna1Gole, druzyna2Gole, dataZakladu);

            ListaMeczyDAO listaMeczyDAO = new ListaMeczyDAO(jdbcConnection);
            Mecz mecz = listaMeczyDAO.get(meczId);
            zaklad.setMecz(mecz);

            listaZakladow.add(zaklad);
        }

        resultSet.close();
        disconnect();
        return listaZakladow;
    }

    public void wstawZaklad(Zaklad zaklad) throws SQLException {

        String sql = "INSERT INTO ZAKLAD (MECZ_ID, UZYTKOWNIK_NAME,UZYTKOWNIK_PIN, DRUZYNA1_GOLE, DRUZYNA2_GOLE) VALUES (?,?,?,?,?)";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setInt(1, zaklad.getMeczId());
        statement.setString(2, zaklad.getUzytkownikName());
        statement.setString(3, zaklad.getUzytkownikPin());
        statement.setString(4, zaklad.getdruzyna1Gole());
        statement.setString(5, zaklad.getdruzyna2Gole());

        statement.executeUpdate();

        statement.close();
        disconnect();
    }

    public int liczbaZakladow(int id, String nazwaUzytkownika, String pin) throws SQLException {

        String sql = "SELECT * FROM ZAKLAD WHERE UZYTKOWNIK_NAME = ? AND MECZ_ID = ? AND UZYTKOWNIK_PIN = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setString(1, nazwaUzytkownika);
        statement.setInt(2, id);
        statement.setString(3, pin);

        ResultSet resultSet = statement.executeQuery();

        resultSet.last();
        int liczbaWierszy = resultSet.getRow();

        resultSet.close();
        statement.close();
        disconnect();

        return liczbaWierszy;
    }

    public String sprawdzPseudonim(String nazwaUzytkownika) throws SQLException {

        String sql = "SELECT UZYTKOWNIK_PIN FROM ZAKLAD WHERE UZYTKOWNIK_NAME = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setString(1, nazwaUzytkownika);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        String pinUzytkownika;

        if (resultSet.getRow() > 0) {

            pinUzytkownika = resultSet.getString(1);
        } else {
            pinUzytkownika = null;
        }

        resultSet.close();
        statement.close();
        disconnect();

        return pinUzytkownika;

    }

    public boolean getBet(int id, String nazwaUzytkownika, String pin, String bet1, String bet2) throws SQLException {

        String sql = "SELECT * FROM ZAKLAD WHERE MECZ_ID = ? AND UZYTKOWNIK_NAME = ? AND UZYTKOWNIK_PIN = ? AND DRUZYNA1_GOLE = ? AND DRUZYNA2_GOLE = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setInt(1, id);
        statement.setString(2, nazwaUzytkownika);
        statement.setString(3, pin);
        statement.setString(4, bet1);
        statement.setString(5, bet2);

        ResultSet resultSet = statement.executeQuery();
        resultSet.last();

        boolean betIstnieje;

        if(resultSet.getRow() > 0)
        {
            betIstnieje = true;
        }
        else {
            betIstnieje = false;
        }

        resultSet.close();
        statement.close();
        disconnect();

        return betIstnieje;

    }

}



