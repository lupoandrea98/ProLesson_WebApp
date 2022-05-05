package servlet;

import java.util.ArrayList;

public class AvaiablePrenotation {

    private String corso;
    private String docente;
    private String giorno;
    private int orario;

    private int avaiable;

    public static ArrayList<AvaiablePrenotation> avPren = AvaiablePrenotation.setAvPren();
    public AvaiablePrenotation(String corso, String docente, String giorno, int orario, int avaiable) {
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

    @Override
    public String toString() {
        return this.corso + " " + this.docente + " " + this.giorno + " " + this.orario;
    }

    public void setAvaiable(int avaiable) {
        this.avaiable = avaiable;
    }

    private static ArrayList<AvaiablePrenotation> setAvPren() {
        ArrayList<AvaiablePrenotation> avPren = new ArrayList<>();
        avPren.add(new AvaiablePrenotation("matematica", "Cannavò", "Lun", 15, 0));
        avPren.add(new AvaiablePrenotation("informatica", "DiBitonto", "Mer", 15, 0));
        avPren.add(new AvaiablePrenotation("informatica", "DiBitonto", "Mar", 17, 0));
        avPren.add(new AvaiablePrenotation("fisica", "Cantenne", "Ven", 16, 0));
        avPren.add(new AvaiablePrenotation("sviluppo", "Panicchi", "Lun", 18, 0));
        avPren.add(new AvaiablePrenotation("psicologia sociale", "trotta", "Lun", 18, 0));
        avPren.add(new AvaiablePrenotation("matematica", "Cannavò", "Gio", 18, 0));
        avPren.add(new AvaiablePrenotation("informatica", "DiBitonto", "Lun", 15, 0));
        avPren.add(new AvaiablePrenotation("informatica", "DiBitonto", "Mar", 17, 0));
        avPren.add(new AvaiablePrenotation("fisica", "Cantenne", "Ven", 16, 0));
        avPren.add(new AvaiablePrenotation("sviluppo", "Panicchi", "Lun", 18, 0));
        avPren.add(new AvaiablePrenotation("psicologia sociale", "trotta", "Lun", 18, 0));
        avPren.add(new AvaiablePrenotation("informatica", "DiBitonto", "Lun", 15, 0));
        avPren.add(new AvaiablePrenotation("informatica", "DiBitonto", "Mar", 17, 0));
        avPren.add(new AvaiablePrenotation("fisica", "Cantenne", "Gio", 16, 0));
        avPren.add(new AvaiablePrenotation("sviluppo", "Panicchi", "Mar", 15, 0));
        avPren.add(new AvaiablePrenotation("psicologia sociale", "trotta", "Lun", 18, 0));
        return avPren;
    }

    //TODO: Quando vado a prenotare una di queste lezioni mi basterà settare quella avaiable a 1
}
