package servlet;

import com.google.gson.Gson;
import dao.DAO;
import dao.Docente;
import dao.Utente;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

@WebServlet(name = "insuser", value = "/insuser")
public class InsertUser extends HttpServlet {

    public void init() {
        DAO.registerDriver();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();

        try{
            insertUser(request, response);
        }catch (Exception e){
            System.out.println("Impossibile inserire un nuovo utente" + e);
            out.println(gson.toJson(false));
            out.flush();
            out.close();
        }

    }

    public void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean success = false;
        int selectAdmin = 0;
        HttpSession session = request.getSession();
        String finta_sessione = "admin";
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        Gson gson = new Gson();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(request.getParameter("selectAdmin").equals("true"))
            selectAdmin = 1;
        System.out.println("Ruolo di sessione " + session.getAttribute("ruolo"));
        if(session.getAttribute("ruolo") == null)
            System.out.println("Ruolo di sessione non presente, impossibile procedere con le operazioni desiderate");

        System.out.println("is admin " + selectAdmin);
        if(finta_sessione.equals("admin")) {
            if(username != null && password != null) {
                Utente.insertDB(username, password, selectAdmin);
                success = true;
            }else{
                System.out.println("Parametri dal form nulli");
            }
        }else
            System.out.println("Accesso non autorizzato all'inserimento utente");

        try {
            out.println(gson.toJson(success));
        }finally {
            out.flush();
            out.close();
        }
    }


}
