package servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@WebServlet(name = "booking", value = "/booking")
public class Booking extends HttpServlet {

    public void init() {
        DAO.registerDriver();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            booking(request, response);
        }catch (Exception e){
            System.out.println("booking -> " + e);
        }

    }

    public void booking(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
        Quando funzioneranno, prenderò l'account dalla sessione utente

        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("account");

        Per ora metto manualmente l'utente al quale mi riferisco
         */

        String user = "lupoandrea98";       //Da rimuovere
        boolean check = false;
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");

        AvaiableLesson[] booked = gson.fromJson(request.getParameter("booking"), AvaiableLesson[].class);

        //Aggiorno la lista delle prenotazioni disponibili, segnando come non disponibili quelle prenotate
        for(AvaiableLesson av : AvaiableLesson.avPren) {
            for(AvaiableLesson b : booked) {
                if(av.getCorso().equals(b.getCorso()) && av.getDocente().equals(b.getDocente()))
                    av.setAvaiable(1);
            }
        }

        if(booked != null){
            for (AvaiableLesson avaiableLesson : booked) {
                //Vado a recuperare gli ID per inserire la prenotazione in DB
                Prenotazioni.insertDB(Utente.getId_byUsername(user), Docente.getId_bySurname(avaiableLesson.getDocente()), Corso.getId_byTitolo(avaiableLesson.getCorso()), avaiableLesson.getGiorno(), avaiableLesson.getOrario());
            }
            check = true;
        }else {
            System.out.println("Nessuna richiesta di prenotazione");
        }

        try{
            out.println(gson.toJson(gson.toJson(check)));
        }finally {
            out.flush();
            out.close();
        }

    }

}
