
import pl.polsl.Model.ButtonModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "HistoryServlet", urlPatterns = {"/HistoryService"})
public class HistoryServlet extends HttpServlet {

    private final ButtonModel buttonModel = new ButtonModel();
   

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>History of Moves</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>History of Moves</h1>");

            // Pobranie historii z modelu
            List<String> history = buttonModel.getHistory();
            if (history.isEmpty()) {
                out.println("<p>No moves have been made yet.</p>");
            } else {
                out.println("<ul>");
                for (String move : history) {
                    out.println("<li>" + move + "</li>");
                }
                out.println("</ul>");
            }

            // Link do powrotu do gry
            out.println("<a href='CalculationService'>Back to Sudoku</a>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}
