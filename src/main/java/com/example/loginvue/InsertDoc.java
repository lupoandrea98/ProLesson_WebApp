package com.example.loginvue;

import dao.DAO;
import dao.Docente;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "insdoc", value = "/insdoc")
public class InsertDoc extends HttpServlet {

    public void init() { DAO.registerDriver(); }  //Se non registro i driver non posso entrare nel DB
    /*
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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


}
