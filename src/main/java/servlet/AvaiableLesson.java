package servlet;

import java.util.ArrayList;

public class AvaiableLesson {

    private String corso;
    private String docente;
    private String giorno;
    private int orario;
    private int avaiable;   //0=attiva, 1=effettuata, 2=disdetta
    public static ArrayList<AvaiableLesson> avPren = AvaiableLesson.setAvPren();
    public AvaiableLesson(String corso, String docente, String giorno, int orario, int avaiable) {
        this.corso = corso;
        this.docente = docente;
        this.giorno = giorno;
        this.orario = orario;
        this.avaiable = avaiable;
    }

    public String getCorso() {
        return corso;
    }

    public String getDocente() {
        return docente;
    }

    public String getGiorno() {
        return giorno;
    }

    public int getOrario() {
        return orario;
    }

    public int isAvaiable() { return avaiable; }

    @Override
    public String toString() {
        return this.corso + " " + this.docente + " " + this.giorno + " " + this.orario;
    }

    public void setAvaiable(int avaiable) {
        this.avaiable = avaiable;
    }

    private static ArrayList<AvaiableLesson> setAvPren() {
        ArrayList<AvaiableLesson> avPren = new ArrayList<>();
        avPren.add(new AvaiableLesson("matematica", "Cannavò", "Lun", 15, 0));
        avPren.add(new AvaiableLesson("informatica", "DiBitonto", "Lun", 15, 0));
        avPren.add(new AvaiableLesson("sicurezza", "Bozzardini", "Lun", 15, 0));
        avPren.add(new AvaiableLesson("sicurezza", "Bozzardini", "Lun", 18, 0));
        avPren.add(new AvaiableLesson("matematica", "Cannavò", "Lun", 18, 0));
        avPren.add(new AvaiableLesson("psicologia sociale", "Trotta", "Lun", 18, 0));
        avPren.add(new AvaiableLesson("sviluppo", "Panichi", "Lun", 18, 0));
        avPren.add(new AvaiableLesson("informatica", "DiBitonto", "Lun", 18, 0));
        avPren.add(new AvaiableLesson("sviluppo", "Panichi", "Mar", 15, 0));
        avPren.add(new AvaiableLesson("informatica", "DiBitonto", "Mar", 17, 0));
        avPren.add(new AvaiableLesson("psicologia sociale", "Trotta", "Mar", 17, 0));
        avPren.add(new AvaiableLesson("sviluppo", "Panichi", "Mar", 17, 0));
        avPren.add(new AvaiableLesson("matematica", "Cannavò", "Mar", 17, 0));
        avPren.add(new AvaiableLesson("informatica", "DiBitonto", "Mer", 15, 0));
        avPren.add(new AvaiableLesson("sicurezza", "Bozzardini", "Gio", 16, 0));
        avPren.add(new AvaiableLesson("matematica", "Cannavò", "Gio", 18, 0));
        avPren.add(new AvaiableLesson("sicurezza", "Bozzardini", "Ven", 16, 0));
        avPren.add(new AvaiableLesson("psicologia sociale", "Trotta", "Ven", 16, 0));
        avPren.add(new AvaiableLesson("matematica", "Cannavò", "Ven", 16, 0));

        return avPren;
    }

}
