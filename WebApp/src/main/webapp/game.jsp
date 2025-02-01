<%@ page import="java.util.List" %>
<%@ page import="pl.polsl.entities.PlayerEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Game Database</title>
</head>
<body>
    <h2>Lista graczy w bazie danych</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nick</th>
        </tr>

        <%
            List<PlayerEntity> players = (List<PlayerEntity>) request.getAttribute("players");
            if (players != null) {
                for (PlayerEntity player : players) {
        %>
        <tr>
            <td><%= player.getId() %></td>
            <td><%= player.getName() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="2">Brak danych.</td>
        </tr>
        <%
            }
        %>
    </table>

    <br>
    <a href="CalculationService">Powrót do gry</a>
</body>
</html>