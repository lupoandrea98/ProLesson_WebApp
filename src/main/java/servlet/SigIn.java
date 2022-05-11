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
@WebServlet(name = "sigin", value = "/sigin")

public class SigIn extends HttpServlet{
    public void init() {
        DAO.registerDriver();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Utente> utenti = Utente.queryDB();
        sigin_post(request, response, utenti);
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
}
