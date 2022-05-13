package servlet;

import com.google.gson.Gson;
import dao.DAO;
import dao.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "login", value = "/login")
public class LogIn extends HttpServlet {

    public void init() {
        DAO.registerDriver();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Utente> utenti = Utente.queryDB();
        login_post(request, response, utenti);
    }

    private void login_post(HttpServletRequest request, HttpServletResponse response, ArrayList<Utente> utenti) throws IOException {
        //Configuro la response
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        Gson gson = new Gson();

        //Prelevo i dati provenienti dalla post
        String account = request.getParameter("account");
        String pw = request.getParameter("password");
        //Variabile che attesta il successo o il fallimento del login
        boolean check = false;
        //Stampo in console i dati ricevuti
        System.out.println("ricevuti " + account + " " + pw);
        //Creo una HttpSession nuova
        HttpSession session = request.getSession();
        if (account != null) {
            Utente exists = Utente.exist(utenti, account, pw);
            if (exists != null) {  //Se l'account esiste allora imposto gli attributi di sessione con i suoi valori
                session.setAttribute("account", exists);
                session.setAttribute("ruolo", exists.getRuolo());

                System.out.println(account + " ha loggato come " + exists.getAdmin());
                check = true;
            } else {                //Altrimenti imposto delle stringhe vuote
                session.setAttribute("account", "");
                session.setAttribute("password", "");
                session.setAttribute("ruolo", "ospite");

                System.out.println(account + " non è registrato");
            }
        } else {
            System.out.println("account parameter null");
        }
        //Passo alla pagina una risposta tramite Json.
        try {
            ArrayList<Object> JSON = new ArrayList<>();
            String cookieJson = gson.toJson(session.getAttribute("account"));
            String checkJson = gson.toJson(check);
            JSON.add(checkJson);
            JSON.add(cookieJson);
            System.out.println(JSON);
            out.println(JSON);
        } catch(Exception e){
            System.out.println(e);
        }
        finally {
            out.flush();
            out.close();
        }
        System.out.println("Ruolo di sessione memorizzato = " + session.getAttribute("ruolo"));
    }

}