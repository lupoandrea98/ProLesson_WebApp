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
        Gson gson = new Gson();
        boolean check = false;
        HttpSession session = HttpSessionCollector.find(request.getParameter("JSESSIONID"));
        if(session != null) {
            System.out.println("Sessione " + session.getId() + " invalidata");
            session.invalidate();
            check = true;
        }

        try {
            out.println(gson.toJson(check));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));
            out.println(gson.toJson(false));
        } finally {
            out.flush();
            out.close();
        }
    }
}