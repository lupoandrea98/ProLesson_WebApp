package servlet;

import dao.Corso;
import dao.Docente;
import dao.Insegnamenti;
import java.util.ArrayList;

public class AvaiableLesson {

    private String corso;
    private String docente;
    private String giorno;
    private int orario;
    private String state;   //attiva, effettuata, disdetta
    private int avaiable;   //0=disponibile, 1=non disponibile
    public static ArrayList<AvaiableLesson> avPren = AvaiableLesson.setAvPren();
    public AvaiableLesson(String corso, String docente, String giorno, int orario, String state) {
        this.corso = corso;
        this.docente = docente;
        this.giorno = giorno;
        this.orario = orario;
        this.state = state;
        this.avaiable = 0;
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

    public void setState(String  state) {
        this.state = state;
    }

    public void setAvaiable(int avaiable) { this.avaiable = avaiable; }

    private static ArrayList<AvaiableLesson> OldSetAvPren() {
        ArrayList<AvaiableLesson> avPren = new ArrayList<>();
        avPren.add(new AvaiableLesson("matematica", "Cannavò", "Lun", 15, "attiva"));
        avPren.add(new AvaiableLesson("informatica", "DiBitonto", "Lun", 15, "attiva"));
        avPren.add(new AvaiableLesson("sicurezza", "Bozzardini", "Lun", 15, "attiva"));
        avPren.add(new AvaiableLesson("sicurezza", "Bozzardini", "Lun", 18, "attiva"));
        avPren.add(new AvaiableLesson("matematica", "Cannavò", "Lun", 18, "attiva"));
        avPren.add(new AvaiableLesson("psicologia sociale", "Trotta", "Lun", 18, "attiva"));
        avPren.add(new AvaiableLesson("sviluppo", "Panichi", "Lun", 18, "attiva"));
        avPren.add(new AvaiableLesson("informatica", "DiBitonto", "Lun", 18, "attiva"));
        avPren.add(new AvaiableLesson("sviluppo", "Panichi", "Mar", 15, "attiva"));
        avPren.add(new AvaiableLesson("informatica", "DiBitonto", "Mar", 17, "attiva"));
        avPren.add(new AvaiableLesson("psicologia sociale", "Trotta", "Mar", 17, "attiva"));
        avPren.add(new AvaiableLesson("sviluppo", "Panichi", "Mar", 17, "attiva"));
        avPren.add(new AvaiableLesson("matematica", "Cannavò", "Mar", 17, "attiva"));
        avPren.add(new AvaiableLesson("informatica", "DiBitonto", "Mer", 15, "attiva"));
        avPren.add(new AvaiableLesson("sicurezza", "Bozzardini", "Gio", 16, "attiva"));
        avPren.add(new AvaiableLesson("matematica", "Cannavò", "Gio", 18, "attiva"));
        avPren.add(new AvaiableLesson("sicurezza", "Bozzardini", "Ven", 16, "attiva"));
        avPren.add(new AvaiableLesson("psicologia sociale", "Trotta", "Ven", 16, "attiva"));
        avPren.add(new AvaiableLesson("matematica", "Cannavò", "Ven", 16, "attiva"));

        return avPren;
    }

    private static ArrayList<AvaiableLesson> setAvPren() {
        ArrayList<Insegnamenti> insegnamenti = Insegnamenti.queryDB();
        ArrayList<AvaiableLesson> avPren = new ArrayList<>();
        ArrayList<String> giorni = new ArrayList<>();
        ArrayList<Integer> ore = new ArrayList<>();
        giorni.add("Lun");
        giorni.add("Mar");
        giorni.add("Mer");
        giorni.add("Gio");
        giorni.add("Ven");
        ore.add(15);
        ore.add(16);
        ore.add(17);
        ore.add(18);

        for(Insegnamenti i : insegnamenti) {
            String c = Corso.getTitolo_byID(Integer.parseInt(i.getCorso()));
            String d = Docente.getCognome_byID(Integer.parseInt(i.getDocente()));
            for(int k=0; k<giorni.size();k++)
                for(int j=0; j<ore.size(); j++)
                    avPren.add(new AvaiableLesson(c, d, giorni.get(k), ore.get(j), "attiva"));
        }

        return avPren;

    }

}
