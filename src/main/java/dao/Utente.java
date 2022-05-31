package dao;

import java.sql.*;
import java.util.ArrayList;

public class Utente {
    private static DAO dao = new DAO();
    private int id;
    private String account;
    private String psw;
    private int isAdmin;

    public Utente(int id, String username, String password, int ruolo) {
        this.id = id;
        this.account = username;
        this.psw = password;
        this.isAdmin = ruolo;
    }

    public static DAO getDao() {
        return dao;
    }

    public int getId() { return id; }

    public static  int getId_byUsername(String username) {
        ArrayList<Utente> utenti = Utente.queryDB();
        for(Utente u : utenti)
            if(u.getAccount().equals(username))
                return u.getId();

        return -1;
    }

    public String getAccount() {
        return account;
    }

    public static String getAccount_byID(int ID) {
        ArrayList<Utente> utenti = Utente.queryDB();
        for(Utente u : utenti)
            if(u.getId() == ID)
                return u.getAccount();

        return null;
    }

    public String getPsw() {
        return psw;
    }

    public int getRuolo() { return isAdmin; }

    public String getAdmin(){ return (isAdmin == 1) ? "admin" : "utente"; }//restituisce true se l'utente è amministrato

    @Override
    public String toString() {
        return id + " " + account + " " + psw+ " " + this.getAdmin();
    }

    public static ArrayList<Utente> queryDB() {
        Connection conn1 = null;
        ArrayList<Utente> out = new ArrayList<>();
        String query = "SELECT * FROM `utente`";

        try {
            conn1 = DriverManager.getConnection(dao.getUrl1(),dao.getUser(), dao.getPassword());
            if (conn1 != null) {
                System.out.println("Connected to the database");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Utente p = new Utente(rs.getInt("id"), rs.getString("username"),rs.getString("password"), rs.getInt("isAdmin"));
                out.add(p);
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

    //In questo modo dovrei poter inserire utenti
    public static void insertDB(String account, String password, Integer isAdmin) {
        Connection conn1 = null;

        String query = "INSERT INTO `utente` (`id`, `username`, `password`, `isAdmin`) VALUES (NULL, '" + account +
                "', '" + password + "', '" + isAdmin + "');";

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
        System.out.println("New user insert into database correctly");
    }

    public static void removeDB(int id) {
        Connection conn1 = null;

        String query = "DELETE FROM `utente` WHERE `utente`.`id` =" + id + ";";

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

    public static Utente exist(ArrayList<Utente> utenti, String username, String password) {
        for(Utente u : utenti) {
            if(u.getAccount().equals(username))
                if(u.getPsw().equals(password))
                    return u;
        }
        return null;
    }

}
