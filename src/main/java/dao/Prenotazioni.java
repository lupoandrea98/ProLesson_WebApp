package dao;

import java.sql.*;
import java.util.ArrayList;

public class Prenotazioni {

    private static DAO dao = new DAO();
    private int id;
    private String corso;
    private String docente;
    private int utente;
    private String stato;
    private String giorno;
    private int orario;


    public Prenotazioni(String corso, String docente, int utente, String stato, String giorno, int orario) {
        this.corso = corso;
        this.docente = docente;
        this.utente = utente;
        this.stato = stato;
        this.giorno = giorno;
        this.orario = orario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getCorso() {
        return corso;
    }

    public String getDocente() {
        return docente;
    }

    public int getUtente() {
        return utente;
    }

    public String getStato() {
        return stato;
    }
    public String getGiorno() {
        return giorno;
    }

    public int getOrario() {
        return orario;
    }

    @Override
    public String toString() {
        return "Prenotazioni{" +
                "id=" + id +
                ", corso=" + corso +
                ", docente=" + docente +
                ", utente=" + utente +
                ", stato='" + stato + '\'' +
                ", giorno='" + giorno + '\'' +
                ", orario=" + orario +
                '}';
    }

    public static ArrayList<Prenotazioni> queryDB() {
        Connection conn1 = null;
        ArrayList<Prenotazioni> out = new ArrayList<>();
        String query = "SELECT * FROM `prenotazioni`";

        try{
            conn1 = DriverManager.getConnection(dao.getUrl1(),dao.getUser(),dao.getPassword());

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Prenotazioni p = new Prenotazioni(rs.getString("corso"), rs.getString("docente"), rs.getInt("utente"), rs.getString("stato"), rs.getString("giorno"), rs.getInt("orario"));
                p.setId(rs.getInt("id"));
                out.add(p);
            }
        } catch(SQLException e) {
            System.out.println("Query goes wrong -> " + e.getMessage());
        }
        finally {
            if(conn1 != null) {
                try {
                    conn1.close();
                }catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static void insertDB(int nome_utente, String nome_docente, String corso, String giorno, int orario) {
        Connection conn1 = null;
        String query = null;

        //devo controllare che i parametri orario e giorno siano accettabili.
        if(!(giorno.equals("Lun") || giorno.equals("Mar") || giorno.equals("Mer") || giorno.equals("Gio") ||
                giorno.equals("Ven")) && !((15 <= orario) && (orario<=19))){
            System.err.println("Invalid parameter of giorno");
        }else {
            query = "INSERT INTO `prenotazioni` (`id`, `corso`, `docente`, `utente`, `stato`, `giorno`, `orario`) VALUES (NULL, '" + corso +
                    "', '" + nome_docente + "', '" + nome_utente + "', '" + "attiva" + "', '" + giorno + "', '" + orario + "');";
        }

        try {
            conn1 = DriverManager.getConnection(dao.getUrl1(),dao.getUser(), dao.getPassword());

            Statement st = conn1.createStatement();
            if(query!=null) {
                if(st.executeUpdate(query) == 0)
                    System.out.println("something goes wrong in the insert");
            }else
                System.err.println("query is NULL");
        } catch (SQLException e) {
            System.out.println("Insert goes wrong -> " + e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static void removeDB(Prenotazioni pr) {
        Connection conn1 = null;

        String query = "DELETE FROM `prenotazioni` WHERE `prenotazioni`.`id` =" + pr.getId() + ";";

        try {
            conn1 = DriverManager.getConnection(dao.getUrl1(),dao.getUser(), dao.getPassword());

            Statement st = conn1.createStatement();
            if(st.executeUpdate(query) == 0)
                System.out.println("something goes wrong in the delete");

        } catch (SQLException e) {
            System.out.println("Delete goes wrong -> " + e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    //metodo per controllare l'esistenza di una prenotazione
    //Nel caso esista, mi resituirà la prenotazione esistente, altrimenti null
    public static Prenotazioni exist(ArrayList<Prenotazioni> pren, String corso, String docente, int utente, String giorno, int orario) {
        for(Prenotazioni p : pren) {
            if(p.getGiorno().equals(giorno) && p.getOrario() == orario)
                if(p.getDocente().equals(docente))
                    if(p.getCorso().equals(corso))
                        if(p.getUtente() == utente)
                            return p;
        }
        return null;
    }

    //metodo per la modifica dello stato di prenotazione

    public void changeState(String state) {
        Connection conn1 = null;
        String query;
        if(state.equals("attiva") || state.equals("disdetta") || state.equals("effettuata")) {
            query = "UPDATE `prenotazioni` SET `stato`=" + "'" + state + "'" + " WHERE `prenotazioni`.`id` = " + this.getId() + ";";
            try {
                conn1 = DriverManager.getConnection(dao.getUrl1(),dao.getUser(), dao.getPassword());

                Statement st = conn1.createStatement();
                if(st.executeUpdate(query) == 0)
                    System.out.println("something goes wrong during the change state");

            } catch (SQLException e) {
                System.out.println("changeState goes wrong -> " + e.getMessage());
            }
            finally {
                if (conn1 != null) {
                    try {
                        conn1.close();
                    } catch (SQLException e2) {
                        System.out.println(e2.getMessage());
                    }
                }
            }
        }else{
            System.err.println("State not valid");
        }
    }

    //per ciascun corso deve essere possibile visualizzare la lista delle possibili ripetizioni, indicandone i docenti
    //passo per parametro l'arraylist per ridurre al minimo le query verso il db
    public static ArrayList<Prenotazioni> possiblePrenotation(Corso c, ArrayList<Prenotazioni> pren) {

        ArrayList<Prenotazioni> result = new ArrayList<>();
        for(Prenotazioni p : pren) {
            if(p.getCorso().equals(c.getTitolo())) {
                result.add(p);
            }
        }
        return result;
    }

    public static ArrayList<Prenotazioni> userPrenotation(String user) {
        ArrayList<Prenotazioni> allPrenotation = Prenotazioni.queryDB();
        ArrayList<Prenotazioni> userPrenotation = new ArrayList<>();
        for(Prenotazioni p : allPrenotation) {
            if(p.getUtente() == Utente.getId_byUsername(user))
                userPrenotation.add(p);
        }
        return userPrenotation;
    }

}
