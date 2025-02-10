package com.iliasdev.controller;

import com.iliasdev.model.OngoingMatch;
import com.iliasdev.service.OngoingMatchService;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        OngoingMatch ongoingMatch = ongoingMatchService.findById(matchId);

        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
    }
}
