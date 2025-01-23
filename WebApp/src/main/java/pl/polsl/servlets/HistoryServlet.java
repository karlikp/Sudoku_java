package pl.polsl.servlets;
import jakarta.servlet.ServletContext;
import pl.polsl.Model.ButtonModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
 
/**
* The HistoryServlet provides access to the history of moves in the Sudoku game.
*/

@WebServlet(name = "HistoryServlet", urlPatterns = {"/HistoryService"})

public class HistoryServlet extends HttpServlet {
 
    /**
     * Handles the HTTP GET request to display the history of moves.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext context = getServletContext();
        ButtonModel buttonModel = (ButtonModel) context.getAttribute("buttonModel");
        
        List<String> history = buttonModel.getHistory();
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Sudoku History</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Move History</h1>");
            
            if (history.isEmpty()) {
                out.println("<p>No moves have been made yet.</p>");
            } else {
                out.println("<ul>");
                for (String move : history) {
                    out.println("<li>" + move + "</li>");
                }
                out.println("</ul>");
            }
            
            out.println("<a href='CalculationService'>Go Back to Game</a>");
            out.println("</body>");
            out.println("</html>");

        }
    }

}

 