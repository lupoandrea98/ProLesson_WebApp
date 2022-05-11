package servlet;

import com.google.gson.Gson;
import dao.DAO;
import dao.Utente;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.server.ServerCloneException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "logout", value = "/logout")
public class LogOut extends HttpServlet {

    public void init() {
        DAO.registerDriver();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        invalidateSession(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void invalidateSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false); //Non crea una nuova sessione utente se non ne esiste nessuna
        Gson gson = new Gson();
        boolean check = false;
        String json_response = gson.toJson(check);
        try {
            session.invalidate();
            check = true;
            json_response = gson.toJson(check);
            out.println(json_response);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));
            out.println(json_response);
        } finally {
            out.flush();
            out.close();
        }
    }
}