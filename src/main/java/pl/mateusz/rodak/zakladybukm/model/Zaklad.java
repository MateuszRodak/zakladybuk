package pl.mateusz.rodak.zakladybukm.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Zaklad {

    private int id;
    private int meczId;
    private String uzytkownikName;
    private String uzytkownikPin;
    private String druzyna1Gole;
    private String druzyna2Gole;
    private Timestamp dataZakladu;

    private Mecz mecz;

    public Zaklad(int id, int meczId, String uzytkownikName, String uzytkownikPin, String druzyna1Gole, String druzyna2Gole, Timestamp dataZakladu) {
        this.id = id;
        this.meczId = meczId;
        this.uzytkownikName = uzytkownikName;
        this.uzytkownikPin = uzytkownikPin;
        this.druzyna1Gole = druzyna1Gole;
        this.druzyna2Gole = druzyna2Gole;
        this.dataZakladu = dataZakladu;
    }

    public Zaklad(int meczId, String uzytkownikName, String uzytkownikPin, String druzyna1Gole, String druzyna2Gole) {
        this.meczId = meczId;
        this.uzytkownikName = uzytkownikName;
        this.uzytkownikPin = uzytkownikPin;
        this.druzyna1Gole = druzyna1Gole;
        this.druzyna2Gole = druzyna2Gole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeczId() {
        return meczId;
    }

    public void setMeczId(int meczId) {
        this.meczId = meczId;
    }

    public String getUzytkownikName() {
        return uzytkownikName;
    }

    public String getUzytkownikPin() {
        return uzytkownikPin;
    }

    public void setUzytkownikName(String uzytkownikName) {
        this.uzytkownikName = uzytkownikName;
    }

    public void setUzytkownikPin(String uzytkownikPin) {
        this.uzytkownikPin = uzytkownikPin;
    }

    public String getdruzyna1Gole() {
        return druzyna1Gole;
    }

    public String getdruzyna2Gole() {
        return druzyna2Gole;
    }

    public void setdruzyna1Gole(String druzyna1Gole) {
        this.druzyna1Gole = druzyna1Gole;
    }

    public void setdruzyna2Gole(String druzyna2Gole) {
        this.druzyna2Gole = druzyna2Gole;
    }

    public Timestamp getDataZakladu() {
        return dataZakladu;
    }

    public void setDataZakladu(Timestamp dataZakladu) {
        this.dataZakladu = dataZakladu;
    }

    public Mecz getMecz() {
        return mecz;
    }

    public void setMecz(Mecz mecz) {
        this.mecz = mecz;
    }

}
