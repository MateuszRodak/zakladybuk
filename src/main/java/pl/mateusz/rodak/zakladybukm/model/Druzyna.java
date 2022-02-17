package pl.mateusz.rodak.zakladybukm.model;

public class Druzyna {

    private int id;
    private String nazwaDruzyny;

    public Druzyna(int id, String nazwaDruzyny) {
        this.id = id;
        this.nazwaDruzyny = nazwaDruzyny;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwaDruzyny() {
        return nazwaDruzyny;
    }

    public void setNazwaDruzyny(String nazwaDruzyny) {
        this.nazwaDruzyny = nazwaDruzyny;
    }
}
