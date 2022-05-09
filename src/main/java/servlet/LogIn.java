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
    private final int ID = 0;

    public void init() {
        DAO.registerDriver();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Richiamata correttamente");
        String action = request.getParameter("action");
        ArrayList<Utente> utenti = Utente.queryDB();
        switch (action) {
            case "login":
                login_post(request, response, utenti);
                break;
            case "sigin":
                sigin_post(request, response, utenti);
                break;
            default:
                System.out.println("action not valid");
        }
    }

    private void sigin_post(HttpServletRequest request, HttpServletResponse response, ArrayList<Utente> utenti) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        String account = request.getParameter("account");
        String pw = request.getParameter("password");
        boolean check_reg = false;
        boolean check_user = false;

        System.out.println("ricevuti " + account + " " + pw);

        if (account != null) {
            Utente exists = Utente.exist(utenti, account, pw);
            if (exists != null) {
                System.out.println("Account già esistente!");
                check_user = true;
                //L'UTENTE ESISTE E DEVO MANDARE AL FRONT L'AVVISO NOME UTENTE ESISTENTE.

            } else {
                //Posso inserire l'utente nel database.
                Utente.insertDB(account, pw, 0);
                check_reg = true;
            }

        }
        ArrayList<Boolean> checks = new ArrayList<Boolean>();
        checks.add(check_user);
        checks.add(check_reg);
        try {
            Gson gson = new Gson();
            String checkString = gson.toJson(checks);
            System.out.println(checkString);
            out.println(checkString);
        } finally {
            out.flush();
            out.close();
        }

    }

    private void login_post(HttpServletRequest request, HttpServletResponse response, ArrayList<Utente> utenti) throws IOException {
        //Configuro la response
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        Gson gson = new Gson();
        Cookie user = null;
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
                request.setAttribute("account", account);
                request.setAttribute("password", pw);
                session.setAttribute("ruolo", exists.getRuolo());
                System.out.println(account + " ha loggato come " + exists.getAdmin());
                //Creo un oggetto cookie con il nome utente
                user = new Cookie("user", account);
                System.out.println("cookie: " + user.getValue());
                //Aggiungo il cookie utente alla response
                response.addCookie(user);
                check = true;
            } else {                //Altrimenti imposto delle stringhe vuote
                request.setAttribute("account", "");
                request.setAttribute("password", "");
                session.setAttribute("ruolo", "ospite");
                System.out.println(account + " non è registrato");
                user = new Cookie("user", "ospite");
                //response.addCookie(user);
                //TODO: Perchè non mi vanno i cookie nella response?
            }
        } else {
            System.out.println("account parameter null");
        }
        //Passo alla pagina una risposta tramite Json.
        try {
            ArrayList<String> JSON = new ArrayList<>();
            String cookieJson = gson.toJson(user);
            String checkJson = gson.toJson(check);
            JSON.add(checkJson);
            JSON.add(cookieJson);
            System.out.println(JSON);
            out.println(JSON);
        } finally {
            out.flush();
            out.close();
        }
        System.out.println("Ruolo di sessione memorizzato = " + session.getAttribute("ruolo"));

    }

}