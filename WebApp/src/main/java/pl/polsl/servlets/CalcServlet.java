package pl.polsl.servlets;

import pl.polsl.Model.ButtonModel;
import pl.polsl.Model.DifficultyLevel;
import pl.polsl.Model.InvalidDifficultyLevelException;
import pl.polsl.Model.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * The CalcServlet handles user requests for a Sudoku game.
 * It processes the submitted form data and generates a 9x9 Sudoku grid in HTML format.
 * The servlet also interacts with the ButtonModel to manage the game state.
 */
@WebServlet(name = "CalcServlet", urlPatterns = {"/CalculationService"})
public class CalcServlet extends HttpServlet {

    // Instance of the ButtonModel class for managing the Sudoku grid
    private final ButtonModel buttonModel = new ButtonModel();
    
    /**
     * Handles the HTTP POST request for starting the Sudoku game.
     * It processes the form data, generates the Sudoku grid, and sends it to the client.
     *
     * @param request  the HttpServletRequest containing the user's data
     * @param response the HttpServletResponse to send the generated HTML
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs during the response generation
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Pobierz współrzędne i wartość z formularza
    String rowParam = request.getParameter("row");
    String colParam = request.getParameter("col");
    String valueParam = request.getParameter("value");

    if (rowParam != null && colParam != null && valueParam != null) {
        try {
            int row = Integer.parseInt(rowParam);
            int col = Integer.parseInt(colParam);
            int value = Integer.parseInt(valueParam);

            // Walidacja wartości
            if (!buttonModel.isValueValid(row, col, value)) {
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<h3>Error: Invalid move at row " + (row + 1) + ", column " + (col + 1) + "</h3>");
                    out.println("<a href='CalculationService'>Go Back</a>");
                    out.println("</body>");
                    out.println("</html>");
                }
                return;
            }

            // Aktualizacja modelu
            buttonModel.setValue(row * 9 + col, value);

        } catch (NumberFormatException e) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<html>");
                out.println("<body>");
                out.println("<h3>Error: Invalid input format.</h3>");
                out.println("<a href='CalculationService'>Go Back</a>");
                out.println("</body>");
                out.println("</html>");
            }
            return;
        }
    }

    // Odswież planszę
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Sudoku Game</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Sudoku Game</h1>");

        // Generowanie planszy
        List<List<Integer>> grid = buttonModel.getCurrentGrid();
        for (int i = 0; i < 9; i++) {
            out.println("<div>");
            for (int j = 0; j < 9; j++) {
                int value = grid.get(i).get(j);
                String cellValue = value == 0 ? "" : String.valueOf(value);

                // Formularz dla pojedynczej komórki
                out.println("<form action='CalculationService' method='POST' style='display:inline;'>");
                out.println("<input type='hidden' name='row' value='" + i + "' />");
                out.println("<input type='hidden' name='col' value='" + j + "' />");
                out.println("<input type='text' name='value' value='" + cellValue + "' maxlength='1' size='1' onchange='this.form.submit();' />");
                out.println("</form>");
            }
            out.println("</div>");
        }

        out.println("</body>");
        out.println("</html>");
        
    // Dodanie przycisku "Historia"
        out.println("<form action='HistoryService' method='GET'>");
        out.println("<button type='submit'>Historia</button>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
        
    }
    
    
}


    /**
     * Handles the HTTP GET request to display the Sudoku grid.
     * Delegates processing to the POST method to avoid code duplication.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
