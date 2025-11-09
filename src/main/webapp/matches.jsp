<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>
</head>

<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="index.jsp">Home</a>
                <a class="nav-link" href="matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
            <form action=${pageContext.request.contextPath}/matches method="get" style="width: 100%">
                <input class="input-filter" name="filter_by_player_name" placeholder="Filter by name" type="text" />
            </form>
            <div>
                <a href="matches">
                    <button class="btn-filter">Reset Filter</button>
                </a>
            </div>
        </div>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>

            <c:forEach var="match" items="${matches}">
                <tr>
                    <td>${match.player1.name}</td>
                    <td>${match.player2.name}</td>
                    <td><span class="winner-name-td">${match.winner.name}</span></td>
                </tr>

            </c:forEach>
        </table>

        <div class="pagination">

            <c:choose>
                <c:when test="${currentPage > 1}">
                    <a class="prev" href="?page=${currentPage - 1}${filterParam}"><</a>
                </c:when>
                <c:otherwise>
                    <a class="prev disabled"><</a>
                </c:otherwise>
            </c:choose>


            <c:forEach var="i" begin="1" end="${totalPages}">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <a class="num-page current" href="#">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a class="num-page" href="?page=${i}${filterParam}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>



            <c:choose>
                <c:when test="${currentPage < totalPages}">
                    <a class="next" href="?page=${currentPage + 1}${filterParam}">></a>
                </c:when>
                <c:otherwise>
                    <a class="next disabled">></a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</main>
</body>
</html>
