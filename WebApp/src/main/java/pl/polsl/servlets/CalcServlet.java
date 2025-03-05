    package pl.polsl.servlets;

    import jakarta.persistence.EntityManager;
    import jakarta.persistence.Persistence;
    import jakarta.persistence.PersistenceException;
    import jakarta.servlet.ServletContext;
    import pl.polsl.Model.ButtonModel;
    import pl.polsl.Model.DifficultyLevel;
    import pl.polsl.Model.InvalidDifficultyLevelException;
    import pl.polsl.Model.Player;
    import pl.polsl.entities.PlayerEntity;


    import jakarta.persistence.EntityManager;
    import jakarta.persistence.EntityTransaction;
    import jakarta.persistence.Persistence;
    import jakarta.persistence.PersistenceException;
    import jakarta.servlet.ServletContext;
    import java.io.IOException;
    import java.io.PrintWriter;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.Cookie;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;

    import pl.polsl.entities.*;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.util.List;

    /**
     * The CalcServlet handles user requests for a Sudoku game.
     * It processes the submitted form data and generates a 9x9 Sudoku grid in HTML format.
     * The servlet also interacts with the ButtonModel to manage the game state.
     */
    @WebServlet(name = "CalcServlet", urlPatterns = {"/CalculationService"})
    public class CalcServlet extends HttpServlet {

       private final ButtonModel buttonModel = new ButtonModel();
        private boolean logIn = false;
        private EntityManager em = null;
        private GameEntity currentGame = null;

        /**
         * Handles the HTTP POST request for starting the Sudoku game.
         * It processes the form data, generates the Sudoku grid, and sends it to the client.
         *
         * @param request  the HttpServletRequest containing the user's data
         * @param response the HttpServletResponse to send the generated HTML
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs during the response generation
         */
        ;

        // one-time opening of entity manager
        public void init() throws ServletException {
        try {
            System.out.println("DEBUG");
            
            ServletContext ctx = getServletContext();
            ctx.setAttribute("buttonModel", buttonModel);

            em = (EntityManager) ctx.getAttribute("entityManager");
            if (em == null) {
                System.out.println("Inicjalizacja EntityManager");
                var emf = Persistence.createEntityManagerFactory("sudoku_persistence_unit");
                em = emf.createEntityManager();
                ctx.setAttribute("entityManager", em);
            }
        } catch (PersistenceException e) {
            throw new ServletException("Błąd inicjalizacji EntityManager: " + e.getMessage(), e);
        }
    }

          /**
         * Handles the HTTP POST request for starting or updating the Sudoku game.
         * Processes form data, updates the game state, and generates the Sudoku grid.
         *
         * @param request  the HttpServletRequest containing the user's data
         * @param response the HttpServletResponse to send the generated HTML
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException      if an I/O error occurs during the response generation
         */     
       @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            // Read last visit cookie
            String lastVisit = null;
            
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("lastVisit".equals(cookie.getName())) {
                        lastVisit = cookie.getValue();
                        break;
                    }
                }
            }

            // Save a new visit timestamp cookie
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = LocalDateTime.now().format(formatter);
            Cookie newCookie = new Cookie("lastVisit", currentDateTime);
            response.addCookie(newCookie);
            

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                if (lastVisit != null) {
                    out.println("<p>[Cookie] Last activity: " + lastVisit + "</p>");
                }

                ServletContext context = getServletContext();
                ButtonModel buttonModel = (ButtonModel) context.getAttribute("buttonModel");

                // Retrieve and store player data
                if (buttonModel.getDifficultyLevel().equals("default")) {
                    // Retrieve user parameters
                    
                    String name = request.getParameter("name");
                    String password = request.getParameter("password");
                    String difficultyLevel = request.getParameter("difficulty");
                    
                    buttonModel.setDifficultyLevel(difficultyLevel);
                    buttonModel.setCurrentGrid();

                    if (name != null && password != null) {
                PlayerEntity existingPlayer = getPlayerByName(name);

                if (existingPlayer != null) {
                    // 🔍 Sprawdzamy poprawność hasła
                    this.currentGame = new GameEntity(difficultyLevel, existingPlayer);
                    persistObject(this.currentGame);
                    if (existingPlayer.getPassword().equals(password)) {  // W przyszłości warto zahashować hasła
                        request.getSession().setAttribute("user", existingPlayer);
                    } else {
                        out.println("<h3>Error: Incorrect password for user " + name + ".</h3>");
                        return;
                    }
                } else {
                    // 🆕 Tworzenie nowego użytkownika
                    PlayerEntity newPlayer = new PlayerEntity(name, password);
                    persistObject(newPlayer);

                    // Tworzenie nowej gry dla nowego użytkownika
                    if (difficultyLevel != null) {
                        this.currentGame = new GameEntity(difficultyLevel, newPlayer);
                        persistObject(this.currentGame);
                    }

                    request.getSession().setAttribute("user", newPlayer);
                }
            }
        }


        // Retrieve from data
        String rowParam = request.getParameter("row");
        String colParam = request.getParameter("col");
        String valueParam = request.getParameter("value");

     if (rowParam != null && colParam != null && valueParam != null) {
            try {
                int row = Integer.parseInt(rowParam);
                int col = Integer.parseInt(colParam);
                int value = Integer.parseInt(valueParam);

                // Validate value
                if (!buttonModel.isValueValid(row, col, value)) {
                    response.setContentType("text/html;charset=UTF-8");
                        out.println("<html>");
                        out.println("<body>");
                        out.println("<h3>Error: Invalid move at row " + (row + 1) + ", column " + (col + 1) + "</h3>");
                        out.println("<a href='CalculationService'>Go Back</a>");
                        out.println("</body>");
                        out.println("</html>");
                    return;
                }

                // Update model
                buttonModel.setValue(row * 9 + col, value);
                // Updata DB
                
                try {
                    if (this.currentGame == null) {
                        System.err.println("⛔ Błąd: currentGame jest null!");
                        return;
                        }
                    System.out.println("Saving move to database: Row=" + row + ", Col=" + col + ", Value=" + value);
                    MovementEntity movement = new MovementEntity(this.currentGame, value, row, col);
                    persistObject(movement);
                    System.out.println("Move saved successfully.");
                } catch (Exception e) {
                    System.err.println("Error while saving move: " + e.getMessage());
                    e.printStackTrace();
}

            } catch (NumberFormatException e) {
                response.setContentType("text/html;charset=UTF-8");
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<h3>Error: Invalid input format.</h3>");
                    out.println("<a href='CalculationService'>Go Back</a>");
                    out.println("</body>");
                    out.println("</html>");
                return;
            }
        }

        // Refresh grid
        response.setContentType("text/html;charset=UTF-8");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Sudoku Game</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Sudoku Game</h1>");

            // Generate grid
            List<List<Integer>> grid = buttonModel.getCurrentGrid();
            for (int i = 0; i < 9; i++) {
                out.println("<div>");
                for (int j = 0; j < 9; j++) {
                    int value = grid.get(i).get(j);
                    String cellValue = value == 0 ? "" : String.valueOf(value);

                    // Form for a single cell
                    out.println("<form action='CalculationService' method='POST' style='display:inline;'>");
                    out.println("<input type='hidden' name='row' value='" + i + "' />");
                    out.println("<input type='hidden' name='col' value='" + j + "' />");
                    out.println("<input type='text' name='value' value='" + cellValue + "' maxlength='1' size='1' onchange='this.form.submit();' />");
                    out.println("</form>");
                }
                out.println("</div>");      
            }

            generateGamePage(response);

        }
            if (request.getParameter("database") != null) {
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Players Database</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h2>Players List</h2>");
                    out.println("<table border='1'>");
                    out.println("<tr><th>ID</th><th>Name</th><th>Difficulty</th></tr>");

            // Pobranie `EntityManager` z kontekstu servletu
            ServletContext context = getServletContext();
            EntityManager em = (EntityManager) context.getAttribute("entityManager");

            if (em == null) {
                var emf = Persistence.createEntityManagerFactory("sudoku_persistence_unit");
                em = emf.createEntityManager();
                context.setAttribute("entityManager", em);
            }

            // Pobranie listy graczy bezpośrednio z bazy
            List<PlayerEntity> players = em.createQuery("SELECT p FROM PlayerEntity p", PlayerEntity.class).getResultList();

            // Generowanie tabeli HTML
            for (PlayerEntity player : players) {
                out.println("<tr>");
                out.println("<td>" + player.getId() + "</td>");
                out.println("<td>" + player.getName() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br><a href='javascript:window.close();'>Close</a>");
            out.println("</body>");
            out.println("</html>");
        }
            return;
        }

        }

        private void generateGamePage(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            // Przycisk "History"
            out.println("<br>");
            out.println("<form action='HistoryService' method='GET'>");
            out.println("<button type='submit'>View History</button>");
            out.println("</form>");

            // Przycisk "Data Base" - otwiera nową zakładkę z widokiem bazy danych
            out.println("<br>");
            out.println("<button onclick=\"window.open('CalculationService?database=true', '_blank')\">Data Base</button>");

            out.println("</body>");
            out.println("</html>");
        }
    }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            if (request.getParameter("database") != null) {
                showDatabase(response);
                return;
            }

            doPost(request, response);
        }

        @Override
        public void destroy() {
            if (em != null && em.isOpen()) {
                em.close();
            }
            super.destroy();
        }

      // Method to save an object in the database
     private void persistObject(Object object) {
        EntityTransaction transaction = em.getTransaction();
        if (!transaction.isActive()) {
            transaction.begin();
        }
        try {
            em.persist(object);
            transaction.commit();
        } catch (PersistenceException e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Ensure rollback if failure happens
            }
            throw e;
        }
    }

       // Retrieves a player by name
       private PlayerEntity getPlayerByName(String name) {
        try {
            return em.createQuery("SELECT p FROM PlayerEntity p WHERE p.name = :name", PlayerEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println("Nie znaleziono gracza: " + name);
            return null;
        }
    }


       // Displays the database in a new tab
private void showDatabase(HttpServletResponse response) throws IOException {
    response.setContentType("text/html;charset=UTF-8");

    try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Database</title></head><body>");
        
        // Show player table
        out.println("<h2>Players</h2>");
        out.println("<table border='1'><tr><th>ID</th><th>Name</th></tr>");

        List<PlayerEntity> players = em.createQuery("SELECT p FROM PlayerEntity p", PlayerEntity.class).getResultList();
        for (PlayerEntity player : players) {
            out.println("<tr><td>" + player.getId() + "</td><td>" + player.getName() + "</td></tr>");
        }
        out.println("</table>");

        // Show game table
        out.println("<h2>Games</h2>");
        out.println("<table border='1'><tr><th>ID</th><th>Difficulty</th><th>Player ID</th></tr>");

        List<GameEntity> games = em.createQuery("SELECT g FROM GameEntity g", GameEntity.class).getResultList();
        for (GameEntity game : games) {
            out.println("<tr><td>" + game.getId() + "</td><td>" + game.getDifficultyLevel() + "</td><td>" + game.getPlayer().getId() + "</td></tr>");
        }
        out.println("</table>");

        // 🎯 Wyświetlenie tabeli ruchów
        out.println("<h2>Movements</h2>");
        out.println("<table border='1'><tr><th>ID</th><th>Game ID</th><th>Move Digit</th><th>Row</th><th>Column</th></tr>");

        List<MovementEntity> movements = em.createQuery("SELECT m FROM MovementEntity m", MovementEntity.class).getResultList();
        for (MovementEntity movement : movements) {
            out.println("<tr><td>" + movement.getId() + "</td><td>" 
                        + movement.getGame().getId() + "</td><td>" 
                        + movement.getMoveDigit() + "</td><td>" 
                        + movement.getRow() + "</td><td>" 
                        + movement.getColNumber() + "</td></tr>");
        }
        out.println("</table>");

        // Zamknięcie strony
        out.println("<br><a href='javascript:window.close();'>Close</a>");
        out.println("</body></html>");
    }

   }
}



