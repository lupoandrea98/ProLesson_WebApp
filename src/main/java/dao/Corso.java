package dao;

import java.sql.*;
import java.util.ArrayList;

public class Corso {

    private static DAO dao = new DAO();

    private int id;
    private String titolo;

    public Corso(int id, String titolo) {
        this.titolo = titolo;
        this.id = id;
    }

    public int getId() { return id; }

    public static int getId_byTitolo(String titolo) {
        ArrayList<Corso> corsi = Corso.queryDB();
        for(Corso c : corsi)
            if(c.getTitolo().equals(titolo))
                return c.getId();
        return -1;
    }

    public String getTitolo() {
        return titolo;
    }

    public static String getTitolo_byID(int ID) {
        ArrayList<Corso> corsi = Corso.queryDB();
        for(Corso c : corsi)
            if(c.getId() == ID)
                return c.getTitolo();
        return null;
    }

    @Override
    public String toString() {
        return id + " " + titolo;
    }


    public static ArrayList<Corso> queryDB() {
        Connection conn1 = null;
        ArrayList<Corso> out = new ArrayList<>();
        String query = "SELECT * FROM `corso`";

        try {
            conn1 = DriverManager.getConnection(dao.getUrl1(),dao.getUser(), dao.getPassword());

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Corso c = new Corso(rs.getInt("id"),rs.getString("titolo"));
                out.add(c);
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

    //TODO:(CONTROLLARE SE DEVO FARLO DAL TESTO) implementare l'impossibilità di inserire un corso con lo stesso titolo di un altro
    public static void InsertDB(String titolo) {
        Connection conn1 = null;

        String query = "INSERT INTO `corso` (`id`, `titolo`) VALUES (NULL,'" + titolo + "');";

        try {
            conn1 = DriverManager.getConnection(dao.getUrl1(),dao.getUser(), dao.getPassword());


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

    public static void removeDB(int id) {
        Connection conn1 = null;

        String query = "DELETE FROM `corso` WHERE `corso`.`id` = \' " + id + "\';";

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

}
