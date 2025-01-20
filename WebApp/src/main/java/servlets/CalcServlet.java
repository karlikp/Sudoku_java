package servlets;

import pl.polsl.Model.ButtonModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * The CalcServlet handles user requests for a Sudoku game.
 * It processes the submitted form data and generates a 9x9 Sudoku grid in HTML format.
 * The servlet also interacts with the ButtonModel to manage the game state.
 */
@WebServlet(name = "CalcServlet", urlPatterns = {"/CalculationService"})
public class CalcServlet extends HttpServlet {

    // Instance of the ButtonModel class for managing the Sudoku grid
    private ButtonModel buttonModel = new ButtonModel();

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

        // Get user data from the request
        String name = request.getParameter("name");
        String difficulty = request.getParameter("difficulty");

        // Based on the difficulty, we can set up the corresponding Sudoku grid
        buttonModel.setCurrentGrid(); // Default sets up the grid (you can modify for difficulty levels)

        // Set the response content type to HTML
        response.setContentType("text/html;charset=UTF-8");

        // Generate the HTML content
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Sudoku Game</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Welcome, " + name + "!</h1>");
            out.println("<h2>Sudoku - Difficulty: " + difficulty + "</h2>");
            out.println("<form action='CalculationService' method='POST'>");

            // Generate a 9x9 table of buttons for the Sudoku grid
            for (int i = 0; i < 9; i++) {
                out.println("<div>");
                for (int j = 0; j < 9; j++) {
                    int buttonId = i * 9 + j;
                    int buttonValue = buttonModel.getValue(buttonId); // Get the value for the button
                    String buttonText = buttonValue == 0 ? "" : String.valueOf(buttonValue);
                    out.println("<input type='button' value='" + buttonText + "' id='button" + buttonId + "' onclick='updateValue(" + buttonId + ")' />");
                }
                out.println("</div>");
            }

            // Submit button for the form
            out.println("<br><br>");
            out.println("<input type='submit' value='Submit' />");
            out.println("</form>");

            // JavaScript for updating the value of a button in the grid
            out.println("<script>");
            out.println("function updateValue(id) {");
            out.println("    var value = prompt('Enter value for cell (1-9):');");
            out.println("    if (value >= 1 && value <= 9) {");
            out.println("        document.getElementById('button' + id).value = value;");
            out.println("        // Here you can also send the updated value back to the server if needed.");
            out.println("    } else {");
            out.println("        alert('Invalid value. Please enter a number between 1 and 9.');");
            out.println("    }");
            out.println("}");
            out.println("</script>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing the servlet description
     */
    @Override
    public String getServletInfo() {
        return "Sudoku Game Servlet";
    }
}
