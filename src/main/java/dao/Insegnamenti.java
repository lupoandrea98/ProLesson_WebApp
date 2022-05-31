package dao;

import java.sql.*;
import java.util.ArrayList;

public class Insegnamenti {
    //TODO: metodi per aggiungere o rimuovere associzioni tra corsi e docenti
    private static DAO dao = new DAO();
    private String corso;
    private String docente;

    public Insegnamenti(String corso, String docente) {
        this.corso = corso;
        this.docente = docente;
    }

    @Override
    public String toString() {
        return corso + " " + docente;
    }

    public static ArrayList<Insegnamenti> queryDB() {
        Connection conn1 = null;
        ArrayList<Insegnamenti> out = new ArrayList<>();
        String query = "SELECT * FROM `insegnamento`";

        try {
            conn1 = DriverManager.getConnection(dao.getUrl1(),dao.getUser(), dao.getPassword());
            if (conn1 != null) {
                System.out.println("Connected to the database");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Insegnamenti i = new Insegnamenti(rs.getString("corso"), rs.getString("docente"));
                out.add(i);
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
    public static void InsertDB(int idDocente, int idCorso){

        Connection conn1 = null;
        String query = "INSERT INTO `insegnamento` (`corso`, `docente`) VALUES ('" + idCorso + "', '" + idDocente + "');";

        try {
            conn1 = DriverManager.getConnection(dao.getUrl1(),dao.getUser(), dao.getPassword());
            if (conn1 != null) {
                System.out.println("Connected to the database");
            }
            Statement st = conn1.createStatement();
            if(st.executeUpdate(query) == 0)
                System.out.println("something goes wrong in the insert");


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

    public static void removeDB(int idDocente, int idCorso) {
        Connection conn1 = null;
        String query = "DELETE FROM `insegnamento` WHERE `insegnamento`.`corso` =" + idCorso + " AND `insegnamento`.`docente` =" + idDocente + ";";

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
