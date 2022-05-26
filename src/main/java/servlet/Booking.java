package servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.*;

import javax.servlet.GenericServlet;
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
        try{
            getBooked(request, response);
        }catch (Exception e){
            System.out.println("booking -> " + e);
        }
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

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        boolean check = false;
        response.setContentType("application/json");
        //Recupero l'httpsession dalla collezione
        HttpSession session = HttpSessionCollector.find(request.getParameter("JSESSIONID"));

        if(session != null) {
            Utente user = (Utente) session.getAttribute("user");
            if(user != null) {
                System.out.println("Stampo parametri di sessione recuperati: " + session.getAttribute("user") + " " + session.getAttribute("role"));

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
                        Prenotazioni.insertDB(Utente.getId_byUsername(user.getAccount()), Docente.getId_bySurname(avaiableLesson.getDocente()), Corso.getId_byTitolo(avaiableLesson.getCorso()), avaiableLesson.getGiorno(), avaiableLesson.getOrario());
                    }
                    check = true;
                }else {
                    System.out.println("Nessuna richiesta di prenotazione");
                }
            }else {
                System.out.println("User null");
                check = false;
            }
        }

        try{
            out.println(gson.toJson(gson.toJson(check)));
        }finally {
            out.flush();
            out.close();
        }
    }

    public void getBooked(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Utente user = null;

        HttpSession session = HttpSessionCollector.find(request.getParameter("JSESSIONID"));
        if(session != null) {
            user = (Utente) session.getAttribute("user");
        }
        ArrayList<AvaiableLesson> bookedLs = new ArrayList<>();
        if(user != null){
            String giorno = request.getParameter("giorno");
            int orario = Integer.parseInt(request.getParameter("ora"));
            ArrayList<Prenotazioni> prenotazioni = Prenotazioni.queryDB();

            //Prelevo le prenotazioni dell'utente e le trasformo in un formato leggibile
            for(Prenotazioni p : prenotazioni) {
                if(user.getAccount().equals(Utente.getAccount_byID(p.getUtente()))) {
                    if(p.getGiorno().equals(giorno) && p.getOrario() == orario) {
                        AvaiableLesson a = new AvaiableLesson(Corso.getTitolo_byID(p.getCorso()), Docente.getCognome_byID(p.getDocente()), p.getGiorno(), p.getOrario(), p.getStato_avaiable());
                        bookedLs.add(a);
                    }
                }
            }
        }

        try{
            String jsonResp = gson.toJson(bookedLs);
            out.println(jsonResp);
        }finally {
            out.flush();
            out.close();
        }
    }

}
