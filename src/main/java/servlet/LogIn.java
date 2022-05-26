package servlet;

import com.google.gson.Gson;
import dao.DAO;
import dao.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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

        //Prelevo i dati provenienti dalla post
        String account = request.getParameter("account");
        String pw = request.getParameter("password");
        //Configuro la response
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        Gson gson = new Gson();
        //Variabile che attesta il successo o il fallimento del login
        boolean check = false;
        //Creo una una nuova HttpSession
        HttpSession session = request.getSession();
        if (account != null) {
            //Creo una collezione di sessioni
            HttpSessionCollector sessionCollector = new HttpSessionCollector();
            //Stampo in console i dati ricevuti
            System.out.println("ricevuti " + account + " " + pw);
            //Creo un evento HttpSession
            HttpSessionEvent sessionEvent = new HttpSessionEvent(session);
            //Aggiungo la sessione utente creata associata all'evento di cui sopra
            sessionCollector.sessionCreated(sessionEvent);
            //Controllo che l'utente sia presente nel DB
            Utente exists = Utente.exist(utenti, account, pw);
            if (exists != null) {  //Se l'account esiste allora imposto gli attributi di sessione con i suoi valori
                session.setAttribute("user", exists);
                session.setAttribute("role", exists.getAdmin());
                System.out.println(account + " ha loggato come " + exists.getAdmin());
                check = true;
            } else {                //Altrimenti imposto null
                session.setAttribute("user", null);
                session.setAttribute("role", "ospite");
                System.out.println(account + " non è registrato");
            }
        } else {
            System.out.println("account parameter null");
        }
        //Passo alla pagina una risposta tramite Json.
        try {
            ArrayList<String> JSON = new ArrayList<>();
            JSON.add(gson.toJson(check));
            JSON.add(gson.toJson(session.getAttribute("user")));
            JSON.add(gson.toJson(session.getId()));
            System.out.println(JSON);
            out.println(JSON);
        } catch(Exception e){
            System.out.println(e);
        }
        finally {
            out.flush();
            out.close();
        }
        System.out.println("Ruolo di sessione memorizzato = " + session.getAttribute("role"));
    }

}