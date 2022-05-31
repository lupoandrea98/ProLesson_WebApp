package servlet;

import com.google.gson.Gson;
import dao.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "adminpanel", value = "/adminpanel")
public class AdminPanel extends HttpServlet {

    public void init() {
        DAO.registerDriver();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        try{
            switch (request.getParameter("action")) {
                case "insertDoc":
                    insertDoc(request, response);
                    break;
                case "removeDoc":
                    removeDoc(request, response);
                    break;
                case "insertInsegna":
                    insertInsegna(request, response);
                    break;
                case "removeInsegna":
                    removeInsegna(request, response);
                    break;
                case "insertCorso":
                    insertCorso(request, response);
                    break;
                case "removeCorso":
                    removeCorso(request, response);
                    break;
                case "removeUser":
                    removeUser(request, response);
                    break;
                default:
                    System.out.println("AdminAction not valid");
            }
        }catch (Exception e){
            System.out.println("Impossibile eseguire una qualsiasi operazione dell'admin" + e);
            out.println(gson.toJson(false));
            out.flush();
            out.close();
        }

    }
    public void removeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean success = false;
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        HttpSession session = HttpSessionCollector.find(request.getParameter("JSESSIONID"));
        if(session != null) {
            String username = request.getParameter("username");
            String role = (String) session.getAttribute("role");
            if(role.equals("admin")) {
                if(username != null) {
                    Utente.removeDB(Utente.getId_byUsername(username));
                    success = true;
                }else{
                    System.out.println("Parametri dal form nulli");
                }
            }else
                System.out.println("Accesso non autorizzato alla rimozione utente");
        }

        try {
            out.println(gson.toJson(success));
        }finally {
            out.flush();
            out.close();
        }
    }

    private void insertDoc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean success = false;
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        Gson gson = new Gson();
        HttpSession session = HttpSessionCollector.find(request.getParameter("JSESSIONID"));

        if(session != null){
            String role = (String) session.getAttribute("role");
            if(role.equals("admin")) {
                String nome = request.getParameter("docname");
                String cognome = request.getParameter("docsname");
                System.out.println(nome + " " + cognome);
                if(nome != null && cognome != null) {
                    Docente.insertDB(nome, cognome);
                    success = true;
                }else{
                    System.out.println("Parametri dal form nulli");
                }
            }else
                System.out.println("Accesso non autorizzato all'inserimento docente");
        }
        try {
            out.println(gson.toJson(success));
        }finally {
            out.flush();
            out.close();
        }
    }

    public void removeDoc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean success = false;
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        HttpSession session = HttpSessionCollector.find(request.getParameter("JSESSIONID"));
        if(session != null) {
            String role = (String) session.getAttribute("role");
            if(role.equals("admin")) {
                String docname = request.getParameter("docname");
                String docsname = request.getParameter("docsname");
                if(docname != null && docsname != null) {
                    Docente.removeDB(Docente.getId_bySurname(docsname));
                    success = true;
                }else{
                    System.out.println("Parametri dal form nulli");
                }
            }else
                System.out.println("Accesso non autorizzato alla rimozione del docente");
        }

        try {
            out.println(gson.toJson(success));
        }finally {
            out.flush();
            out.close();
        }
    }

    public void insertInsegna(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean success = false;
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        HttpSession session = HttpSessionCollector.find(request.getParameter("JSESSIONID"));
        if(session != null) {
            String role = (String) session.getAttribute("role");
            if(role.equals("admin")) {
                String nome_corso = request.getParameter("nome_corso");
                String docsname = request.getParameter("cognome_docente");
                if(docsname != null && nome_corso != null) {
                    Insegnamenti.InsertDB(Docente.getId_bySurname(docsname), Corso.getId_byTitolo(nome_corso));
                    success = true;
                }else{
                    System.out.println("Parametri dal form nulli");
                }
            }else
                System.out.println("Accesso non autorizzato all'inserimento dell'insegnamento");
        }

        try {
            out.println(gson.toJson(success));
        }finally {
            out.flush();
            out.close();
        }
    }

    public void removeInsegna(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean success = false;
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        HttpSession session = HttpSessionCollector.find(request.getParameter("JSESSIONID"));
        if(session != null) {
            String role = (String) session.getAttribute("role");
            if(role.equals("admin")) {
                String nome_corso = request.getParameter("name_corso");
                String docsname = request.getParameter("docsname");
                if(docsname != null && nome_corso != null) {
                    Insegnamenti.removeDB(Docente.getId_bySurname(docsname), Corso.getId_byTitolo(nome_corso));
                    success = true;
                }else{
                    System.out.println("Parametri dal form nulli");
                }
            }else
                System.out.println("Accesso non autorizzato alla rimozione dell'insegnamento");
        }

        try {
            out.println(gson.toJson(success));
        }finally {
            out.flush();
            out.close();
        }
    }

    public void insertCorso(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean success = false;
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        HttpSession session = HttpSessionCollector.find(request.getParameter("JSESSIONID"));
        if(session != null) {
            String role = (String) session.getAttribute("role");
            if(role.equals("admin")) {
                String titolo = request.getParameter("title");
                if(titolo != null) {
                    Corso.InsertDB(titolo);
                    success = true;
                }else{
                    System.out.println("Parametri dal form nulli");
                }
            }else
                System.out.println("Accesso non autorizzato all'inserimento dell'insegnamento");
        }else
            System.out.println("sessione utente nulla");

        try {
            out.println(gson.toJson(success));
        }finally {
            out.flush();
            out.close();
        }
    }

    public void removeCorso(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean success = false;
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        HttpSession session = HttpSessionCollector.find(request.getParameter("JSESSIONID"));
        if(session != null) {
            String role = (String) session.getAttribute("role");
            if(role.equals("admin")) {
                String title = request.getParameter("name_corso");
                if(title != null) {
                    Corso.removeDB(Corso.getId_byTitolo(title));
                    success = true;
                }else{
                    System.out.println("Parametri dal form nulli");
                }
            }else
                System.out.println("Accesso non autorizzato all'inserimento dell'insegnamento");
        }

        try {
            out.println(gson.toJson(success));
        }finally {
            out.flush();
            out.close();
        }
    }


}
