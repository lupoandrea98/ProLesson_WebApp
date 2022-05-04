package servlet;
import com.google.gson.Gson;
import dao.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.server.ServerCloneException;
import java.util.ArrayList;
@WebServlet(name = "tablebox", value = "/tablebox")
public class TableBox extends HttpServlet {

    public void init() {
        DAO.registerDriver();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getPrenotazioni(request,response);
    }

        public void getPrenotazioni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        int ora = Integer.parseInt(request.getParameter("ora"));
        String giorno = request.getParameter("giorno");
        response.setContentType("application/json");

        ArrayList<dao.Prenotazioni> pren = dao.Prenotazioni.queryDB();
        ArrayList<dao.Prenotazioni> output = new ArrayList<>();
        for (dao.Prenotazioni p : pren) {
            if (p.getGiorno().equals(giorno) && p.getOrario() == ora) {
                output.add(p);
            }
        }

        try {
            Gson gson = new Gson();
            String checkString = gson.toJson(output);
            System.out.println(checkString);
            out.println(checkString);
        }finally {
            out.flush();
            out.close();
        }
    }
}
