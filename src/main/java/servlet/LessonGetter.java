package servlet;
import com.google.gson.Gson;
import dao.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "lessongetter", value = "/lessongetter")
public class LessonGetter extends HttpServlet {

    public void init() {
        DAO.registerDriver();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            if(request.getParameter("ora") == null)
                getPrenotazioniAndroid(request, response);
            else
                getPrenotazioni(request,response);
        }catch (Exception e){
            System.out.println("impossibile prendere le prenotazioni: " + e);
        }
    }
    public void getPrenotazioni(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String giorno = request.getParameter("giorno");
        int ora = Integer.parseInt(request.getParameter("ora"));
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        //Sono queste le prenotazioni da mostrare in tabella sul sito
        ArrayList<AvaiableLesson> avPren = AvaiableLesson.avPren;
        ArrayList<AvaiableLesson> outPren = new ArrayList<>();

        for (AvaiableLesson p : avPren) {
            if (p.getGiorno().equals(giorno) && p.getOrario() == ora && p.isAvaiable() != 1) {
                outPren.add(p);
            }
        }
        try {
            Gson gson = new Gson();
            String checkString = gson.toJson(outPren);
            out.println(checkString);
        }finally {
            out.flush();
            out.close();
        }
    }

    public void getPrenotazioniAndroid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Stesso metodo getPrenotazioni() dove rimuovo l'ora dalla query
        String giorno = request.getParameter("giorno");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        //Sono queste le prenotazioni da mostrare in tabella sul sito
        ArrayList<AvaiableLesson> avPren = AvaiableLesson.avPren;
        ArrayList<AvaiableLesson> outPren = new ArrayList<>();

        for (AvaiableLesson p : avPren) {
            if (p.getGiorno().equals(giorno) && p.isAvaiable() != 1) {
                outPren.add(p);
            }
        }

        try {
            Gson gson = new Gson();
            String checkString = gson.toJson(outPren);
            out.println(checkString);
        }finally {
            out.flush();
            out.close();
        }
    }
}
