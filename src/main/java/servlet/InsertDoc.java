package servlet;

import com.google.gson.Gson;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        try{
            insertDoc(request, response);
        }catch (Exception e){
            System.out.println("Impossibile inserire un nuovo docente" + e);
            out.println(gson.toJson(false));
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


}
