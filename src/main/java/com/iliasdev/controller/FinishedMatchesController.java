package com.iliasdev.controller;

import com.iliasdev.dao.MatchDao;
import com.iliasdev.entity.Match;
import com.iliasdev.model.OngoingMatch;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class FinishedMatchesController extends HttpServlet {

    private static final MatchDao matchDao = MatchDao.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filterName = req.getParameter("filter_by_player_name");

        List<Match> matches = matchDao.getAll();

        req.setAttribute("matches", matches);

        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
