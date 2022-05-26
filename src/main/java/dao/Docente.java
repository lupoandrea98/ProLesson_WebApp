package dao;

import java.sql.*;
import java.util.ArrayList;

public class Docente {
    //TODO: Scrivere metodi per l'inserimento e la rimozione di tuple
    private static DAO dao = new DAO();
    private int id;
    private String nome;
    private String cognome;

    public Docente(int id, String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public static String getCognome_byID(int ID) {
        ArrayList<Docente> docenti = Docente.queryDB();
        for(Docente d : docenti)
            if(d.getId() == ID)
                return d.getCognome();
        return null;
    }

    public int getId() {
        return id;
    }

    public static int getId_bySurname(String cognome) {
        ArrayList<Docente> docenti = Docente.queryDB();
        for(Docente d : docenti)
            if(d.getCognome().equals(cognome))
                return d.getId();
        return -1;
    }

    @Override
    public String toString() {
        return id + " " + nome + " " + cognome;
    }

    public static ArrayList<Docente> queryDB() {
        Connection conn1 = null;
        ArrayList<Docente> out = new ArrayList<>();
        String query = "SELECT * FROM `docente`";

        try {
            conn1 = DriverManager.getConnection(dao.getUrl1(),dao.getUser(), dao.getPassword());
            if (conn1 != null) {
                System.out.println("Connected to the database");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Docente d = new Docente(rs.getInt("id"), rs.getString("nome"),rs.getString("cognome"));
                out.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Query goes wrong -> " + e.getMessage());
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
        return out;
    }

    public static void insertDB(String nome, String cognome) {
        Connection conn1 = null;

        String query = "INSERT INTO `docente` (`id`, `nome`, `cognome`) VALUES (NULL, '" + nome +
                 "', '" + cognome + "');";

        try {
            conn1 = DriverManager.getConnection(dao.getUrl1(),dao.getUser(), dao.getPassword());
            if (conn1 != null) {
                System.out.println("Connected to the database");
            }

            Statement st = conn1.createStatement();
            if(st.executeUpdate(query) == 0)
                System.out.println("Something goes wrong in the insert");


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

    public static void removeDB(Docente doc) {
        Connection conn1 = null;

        String query = "DELETE FROM `docente` WHERE `docente`.`id` =" + doc.getId() + ";";

        try {
            conn1 = DriverManager.getConnection(dao.getUrl1(),dao.getUser(), dao.getPassword());
            if (conn1 != null) {
                System.out.println("Connected to the database");
            }

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
}
