package com.example.loginvue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "login", value = "/login")
public class LogIn extends HttpServlet {

    public void init() {
        DAO.registerDriver();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //System.out.println("Richiamata correttamente");
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                postVueJquery(request, response);
                break;
            case "insertdoc":
                insertDoc(request, response);
                break;
            default:
                System.out.println("No action bounded");
                break;
        }

    }

    private void postVueJquery(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String account = request.getParameter("account");
        String pw = request.getParameter("password");
        Boolean check = false;
        System.out.println("ricevuti " + account + " " + pw);

        if(account != null) {
            ArrayList<Utente> utenti = Utente.queryDB();
            Utente exists = Utente.exist(utenti, account, pw);

            if(exists != null) {  //Se l'account esiste allora imposto gli attributi con i suoi valori
                request.setAttribute("account", account);
                request.setAttribute("password", pw);
                check = true;
            }else{                //Altrimenti imposto delle stringhe vuote
                request.setAttribute("account", "");
                request.setAttribute("password", "");
            }
        }
        //Passo alla pagina una risposta tramite Json.
        try{
            Gson gson = new Gson();
            String checkString = gson.toJson(check);
            System.out.println(checkString);
            out.println(checkString);
        }finally {
            out.flush();
            out.close();
        }

    }

    private void insertDoc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean success = false;
        HttpSession session = request.getSession();
        System.out.println("Ruolo di sessione "+(String)session.getAttribute("ruolo"));
        if(session.getAttribute("ruolo") == null)
            System.out.println("Ruolo di sessione non presente, impossibile procedere con le operazioni desiderate");

        if(session.getAttribute("ruolo").equals("admin")) {

            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");

            if(nome != null && cognome != null) {
                Docente.insertDB(nome, cognome);
                success = true;
            }else{
                System.out.println("Parametri dal form nulli");
            }
        }else
            System.out.println("Non sei amministratore figlio di puttana, muori male");

        //response per avvertire l'utente del fallimento nell'inserire un nuovo docente.
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/plain");
            if(success) out.println("Docente inserito correttamente");
            else out.println("Impossibile inserire il docente");
            out.flush();
        }finally {
            out.close();
        }
    }

    private void postJSJquery(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String account = request.getParameter("account");
        String pw = request.getParameter("password");
        Boolean success = false;

        HttpSession session = request.getSession();
        System.out.println("Sessione attuale = " + session.getAttribute("ruolo"));
        if(account != null) {
            ArrayList<Utente> utenti = Utente.queryDB();
            Utente exist = Utente.exist(utenti, account, pw);
            if(exist != null) {

                session.setAttribute("ruolo", exist.getRuolo());
                success = true;
                System.out.println(account + " ha loggato come " + exist.getRuolo());

            }else {
                session.setAttribute("ruolo", "ospite");
                System.out.println(account + " non è registrato");
                success = true;
            }
        }else {
            System.out.println("Il parametro account è nullo");
        }
        //response per genereare un alert sul successo o fallimento dell'operazione di login.
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/plain");
            if(success) out.println("log in avvenuto con successo");
            else out.println("log in fallito");
            out.flush();
        }finally {
            out.close();
        }

        System.out.println("Ruolo di sessione memorizzato = " + session.getAttribute("ruolo"));

    }

}