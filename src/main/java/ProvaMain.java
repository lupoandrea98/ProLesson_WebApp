import dao.*;

import java.util.ArrayList;
public class ProvaMain {
    static int ID = 0;
    public static void main(String[] args){
        //Proviamo ad interrogare il database e recuperare i dati di un utente.
        DAO.registerDriver();

        ArrayList<Insegnamenti> ins = Insegnamenti.queryDB();
        for(Insegnamenti i : ins) {
            System.out.println(i);
        }

        /*
        System.out.println("============UTENTI=============");
        ArrayList<Utente> utenti = Utente.queryDB();
        for(Utente u : utenti){
            System.out.println(u);
        }

        System.out.println();
        System.out.println("============DOCENTI=============");
        ArrayList<Docente> docenti = Docente.queryDB();
        for(Docente d : docenti){
            System.out.println(d);
        }

        System.out.println();
        System.out.println("============CORSI=============");
        ArrayList<Corso> corsi = Corso.queryDB();
        for(Corso c : corsi){
            System.out.println(c);
        }

        System.out.println();
        System.out.println("============INSEGNAMENTI=============");
        ArrayList<Insegnamenti> ins = Insegnamenti.queryDB();
        for(Insegnamenti i : ins){
            System.out.println(i);
        }
        System.out.println();

        System.out.println("======================PRENOTAZIONI==========================");
        ArrayList<Prenotazioni> pren = Prenotazioni.queryDB();
        for(Prenotazioni p : pren) {
            System.out.println(p);
        }
        System.out.println();

        System.out.println("=========================PRIMA PROVA INSERIMENTI=============================");
        Utente Giacomo = new Utente(ID,"giacomo@agg.com", "rog1", 1);
        //Utente.insertDB(Giacomo);
        Corso Info = new Corso(ID,"Informatica Avanzata");
        //Corso.InsertDB(Info);
        //Docente.insertDB("Francesco", "Bozzardini");
        //Insegnamenti.InsertDB(3,3);

        System.out.println("===================UTENTI===================");
        utenti = Utente.queryDB();
        for(Utente u : utenti){
            System.out.println(u);
        }
        System.out.println("=================CORSI===============");
        corsi = Corso.queryDB();
        for(Corso c : corsi)
            System.out.println(c);

        System.out.println("=================DOCENTI==================");
        docenti = Docente.queryDB();
        for(Docente d : docenti)
            System.out.println(d);

        System.out.println("====================INSEGMENTI=====================");
        ArrayList<Insegnamenti> insegnamenti = Insegnamenti.queryDB();
        for(Insegnamenti i : insegnamenti){
            System.out.println(i);
        }



        /*
        System.out.println("==============PROVA CANCELLAZIONE===============");
        for(Utente u : utenti){
            if(u.getId_utente() == 3)
                Utente.removeDB(u);
        }
        System.out.println("ristampo gli utenti");
        utenti = Utente.queryDB();
        for(Utente u : utenti) {
            System.out.println(u);
        }
         */

    }

}
