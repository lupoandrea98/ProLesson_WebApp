package servlet;

import com.google.gson.Gson;
import dao.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "booking", value = "/booking")
public class Booking extends HttpServlet {

    public void init() {
        DAO.registerDriver();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if(action != null){
            if ("booked".equals(action)) {
                try {
                    getBooked(request, response);
                } catch (Exception e) {
                    System.out.println("booking -> " + e);
                }
            } else if("bookedAndroid".equals(action)){
                try {
                    getBooked2(request, response);
                } catch (Exception e) {
                    System.out.println("bookingAndroid -> " + e);
                }
            }else {
                System.out.println("Action not valid");
            }
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        try{
            switch (action) {
                case "dismiss":
                case "done":
                    changingState(request, response, action);
                    break;
                case "booking":
                    booking(request, response);
                    break;
                case "bookedAndroid":
                    getBooked2(request, response);
                    break;
                default:
                    System.out.println("Action not valid");
            }
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
                        if(av.getCorso().equals(b.getCorso()) && av.getDocente().equals(b.getDocente()) && av.getOrario() == b.getOrario() && av.getGiorno().equals(b.getGiorno()))
                            av.setAvaiable(1);
                    }
                }
                if(booked != null){
                    for (AvaiableLesson avaiableLesson : booked) {
                        //Vado a recuperare gli ID per inserire la prenotazione in DB
                        Prenotazioni.insertDB(Utente.getId_byUsername(user.getAccount()), avaiableLesson.getDocente(), avaiableLesson.getCorso(), avaiableLesson.getGiorno(), avaiableLesson.getOrario());
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
        ArrayList<AvaiableLesson> bookedLs = new ArrayList<>();
        HttpSession session = HttpSessionCollector.find(request.getParameter("JSESSIONID"));
        if(session != null) {
            user = (Utente) session.getAttribute("user");
            if(user != null){
                String giorno = request.getParameter("giorno");
                int orario = Integer.parseInt(request.getParameter("ora"));
                ArrayList<Prenotazioni> prenotazioni = Prenotazioni.queryDB();

                //Prelevo le prenotazioni dell'utente e le trasformo in un formato leggibile
                for(Prenotazioni p : prenotazioni) {
                    if(user.getAccount().equals(Utente.getAccount_byID(p.getUtente()))) {
                        if(p.getGiorno().equals(giorno) && p.getOrario() == orario) {
                            AvaiableLesson a = new AvaiableLesson(p.getCorso(), p.getDocente(), p.getGiorno(), p.getOrario(), p.getStato());
                            bookedLs.add(a);
                        }
                    }
                }
            }
        }else
            System.out.println("Nessuna sessione trovata ");

        try{
            String jsonResp = gson.toJson(bookedLs);
            out.println(jsonResp);
        }finally {
            out.flush();
            out.close();
        }
    }

    public void getBooked2(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Utente user = null;
        ArrayList<AvaiableLesson> bookedLs = new ArrayList<>();
        HttpSession session = HttpSessionCollector.find(request.getParameter("JSESSIONID"));
        if(session != null) {
            user = (Utente) session.getAttribute("user");
            if(user != null){
                String giorno = request.getParameter("giorno");
                ArrayList<Prenotazioni> prenotazioni = Prenotazioni.queryDB();

                //Prelevo le prenotazioni dell'utente e le trasformo in un formato leggibile
                for(Prenotazioni p : prenotazioni) {
                    if(user.getAccount().equals(Utente.getAccount_byID(p.getUtente()))) {
                        if(p.getGiorno().equals(giorno)) {
                            AvaiableLesson a = new AvaiableLesson(p.getCorso(), p.getDocente(), p.getGiorno(), p.getOrario(), p.getStato());
                            bookedLs.add(a);
                        }
                    }
                }
            }
        }else
            System.out.println("Nessuna sessione trovata ");

        try{
            String jsonResp = gson.toJson(bookedLs);
            out.println(jsonResp);
        }finally {
            out.flush();
            out.close();
        }
    }

    public void changingState(HttpServletRequest request, HttpServletResponse response, String action) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Utente user = null;
        boolean check = false;

        HttpSession session = HttpSessionCollector.find(request.getParameter("JSESSIONID"));

        if(session != null) {
            user = (Utente) session.getAttribute("user");
            if(user != null) {

                AvaiableLesson[] dismissed = gson.fromJson(request.getParameter("booking"), AvaiableLesson[].class);
                ArrayList<Prenotazioni> prenotazioni = Prenotazioni.queryDB();

                for(AvaiableLesson av : dismissed) {
                    Prenotazioni p = Prenotazioni.exist(prenotazioni, av.getCorso(), av.getDocente(), Utente.getId_byUsername(user.getAccount()), av.getGiorno(), av.getOrario());
                    if(p != null) {
                        switch (action) {
                            case "dismiss":
                                p.changeState("disdetta");
                                break;
                            case "done":
                                p.changeState("effettuata");
                                break;
                        }
                        check = true;
                    }else
                        check = false;
                }
            }
        }

        try{
            String jsonResp = gson.toJson(check);
            out.println(jsonResp);
        }finally {
            out.flush();
            out.close();
        }
    }

}
