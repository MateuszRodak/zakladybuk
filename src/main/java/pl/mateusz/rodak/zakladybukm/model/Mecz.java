package pl.mateusz.rodak.zakladybukm.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Mecz {

    private int id;
    private String godzinaMeczu;
    private String Miasto;
    private int druzyna1;
    private int druzyna2;

    private Druzyna druzyna1Obj;
    private Druzyna druzyna2Obj;


    public Mecz(int id, Timestamp godzinaMeczu, String miasto, int druzyna1, int druzyna2) {
        this.id = id;
        this.godzinaMeczu = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(godzinaMeczu);
        Miasto = miasto;
        this.druzyna1 = druzyna1;
        this.druzyna2 = druzyna2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGodzinaMeczu() {
        return godzinaMeczu;
    }

    public void setGodzinaMeczu(String godzinaMeczu) {
        this.godzinaMeczu = godzinaMeczu;
    }

    public String getMiasto() {
        return Miasto;
    }

    public void setMiasto(String miasto) {
        Miasto = miasto;
    }

    public int getDruzyna1() {
        return druzyna1;
    }

    public void setDruzyna1(int druzyna1) {
        this.druzyna1 = druzyna1;
    }

    public int getDruzyna2() {
        return druzyna2;
    }

    public void setDruzyna2(int druzyna2) {
        this.druzyna2 = druzyna2;
    }

    public Druzyna getDruzyna1Obj() {
        return druzyna1Obj;
    }

    public void setDruzyna1Obj(Druzyna druzyna1Obj) {
        this.druzyna1Obj = druzyna1Obj;
    }

    public Druzyna getDruzyna2Obj() {
        return druzyna2Obj;
    }

    public void setDruzyna2Obj(Druzyna druzyna2Obj) {
        this.druzyna2Obj = druzyna2Obj;
    }
}
