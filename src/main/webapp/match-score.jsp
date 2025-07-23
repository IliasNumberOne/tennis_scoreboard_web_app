<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
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
        <h1>Current match</h1>
        <div class="current-match-image"></div>
        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">
                        <c:choose>
                            <c:when test="${match.tieBreak}">
                                Tie-Break Points
                            </c:when>
                            <c:otherwise>
                                Points
                            </c:otherwise>
                        </c:choose>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr class="player1">
                    <td class="table-text">
                        ${match.scorePlayer1.player.name}
                        <c:if test="${match.gameOver}">
                            <div>
                                Winner
                            </div>
                        </c:if>

                    </td>
                    <td class="table-text">${match.scorePlayer1.set}</td>
                    <td class="table-text">${match.scorePlayer1.game}</td>
                    <td class="table-text">
                        <c:choose>
                            <c:when test="${match.tieBreak}">
                                ${match.scorePlayer1.tieBreakPoint}
                            </c:when>
                            <c:otherwise>
                                ${match.scorePlayer1.point.pointCode}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="table-text">
                        <form action="${pageContext.request.contextPath}/match-score" method="post">
                            <input type="hidden" name="winnerPoint" value="1">
                            <button class="score-btn" name="uuid" value="${match.uuid}"
                            ${match.gameOver ? 'disabled' : ''}>Score</button>
                        </form>
                    </td>
                </tr>
                <tr class="player2">
                    <td class="table-text">
                        ${match.scorePlayer2.player.name}
                            <c:if test="${match.gameOver}">
                                <div>
                                    Winner
                                </div>
                            </c:if>
                    </td>
                    <td class="table-text">${match.scorePlayer2.set}</td>
                    <td class="table-text">${match.scorePlayer2.game}</td>
                    <td class="table-text">
                        <c:choose>
                            <c:when test="${match.tieBreak}">
                                ${match.scorePlayer2.tieBreakPoint}
                            </c:when>
                            <c:otherwise>
                                ${match.scorePlayer2.point.pointCode}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="table-text">
                        <form action="${pageContext.request.contextPath}/match-score" method="post">
                            <input type="hidden" name="winnerPoint" value="2">
                            <button class="score-btn" name="uuid" value="${match.uuid}"
                            ${match.gameOver ? 'disabled' : ''}>Score</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</main>
</body>
</html>
