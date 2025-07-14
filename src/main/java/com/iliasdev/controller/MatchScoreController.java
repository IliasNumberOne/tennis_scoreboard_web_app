package com.iliasdev.controller;

import com.iliasdev.model.OngoingMatch;
import com.iliasdev.service.OngoingMatchService;
import com.iliasdev.service.ScoreCalculationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {
    private static final OngoingMatchService ongoingMatchService = OngoingMatchService.getINSTANCE();
    private static final ScoreCalculationService scoreCalculationService = ScoreCalculationService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        OngoingMatch ongoingMatch = ongoingMatchService.findById(matchId);

        req.setAttribute("match", ongoingMatch);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchID = UUID.fromString(req.getParameter("uuid"));
        int winnerPoint = Integer.parseInt(req.getParameter("winnerPoint"));

        OngoingMatch ongoingMatch = ongoingMatchService.findById(matchID);

        scoreCalculationService.countPoint(ongoingMatch, winnerPoint);

        resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + matchID);

    }
}
