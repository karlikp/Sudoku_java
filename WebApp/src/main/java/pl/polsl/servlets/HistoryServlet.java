package pl.polsl.servlets;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import pl.polsl.entities.SudokuMoveEntity;
import pl.polsl.entities.SudokuPositionHistoryEntity;
import pl.polsl.model.*;

/**
 * Servlet for displaying the history of Sudoku moves.
 * @version 5.0
 * @author Karol Gryc
 */
@WebServlet(name = "SudokuHistoryServlet", urlPatterns = {"/SudokuHistory"})
public class HistoryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Inserted moves history</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Inserted moves: </h1>");

            // print moves history table
            ServletContext ctx = getServletContext();
            var history = (SudokuPositionHistory)ctx.getAttribute("sudokuHist");
            out.println(getMovesHistoryTable(history));
            out.println("<br>");
            
            // print number of failed attempts
            var failuresNum = getFailuresNumCookieValue(request);
            out.println("Number of failed attempts: " + failuresNum + "<br>");
            out.println("<a href=" + request.getHeader("Referer") + "> Back</a>");
            
            // Fetch Sudoku moves from the database
            for (var moveFromDB : findMoves()) {
                out.println("Found move: " + moveFromDB.getRow() + ", " + moveFromDB.getColumn() + " with value: " + moveFromDB.getValue());
                out.println("<br>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a table with the history of inserted Sudoku moves.
     * @param moveHistory History of inserted Sudoku moves.
     * @return HTML table with the history of inserted Sudoku moves.
     */
    protected String getMovesHistoryTable(SudokuPositionHistory moveHistory) {
        if (moveHistory == null) {
            return "No history found";
        }
        
        var history = moveHistory.getHistory();
        if (history.isEmpty()) {
            return "No history found";
        }
        
        var table = new StringBuilder();
        table.append("<table border='1'>");
        table.append("<th>Row</th><th>Column</th><th>Value</th>");
        for (var entry : history) {
            table.append("<tr>");
            table.append("<td>" + entry.getRow() + "</td>");
            table.append("<td>" + entry.getColumn() + "</td>");
            table.append("<td>" + entry.getValue() + "</td>");
            table.append("</tr>");
        }
        
        table.append("</table>");
        return table.toString();
    }
    
    /**
     * Returns the number of failed attempts from the cookie.
     * @param request Servlet request.
     * @return Number of failed attempts.
     */
    private Integer getFailuresNumCookieValue(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Integer failuresNum = 0;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("failuresNum")) {
                    try {
                        failuresNum = Integer.parseInt(cookie.getValue());
                    } catch(NumberFormatException e) {}
                    break;
                }
            }
        }
        return failuresNum;
    }
    
    public List<SudokuMoveEntity> findMoves() {
        List<SudokuMoveEntity> moveList = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sudoku_persistance_unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT m FROM SudokuMoveEntity m");
            moveList = query.getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return moveList;
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
