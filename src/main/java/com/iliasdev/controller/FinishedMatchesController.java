package com.iliasdev.controller;

import com.iliasdev.dao.MatchDao;
import com.iliasdev.entity.Match;
import com.iliasdev.entity.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/matches")
public class FinishedMatchesController extends HttpServlet {

    private static final MatchDao matchDao = MatchDao.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filterName = req.getParameter("filter_by_player_name");
        boolean hasFilter = filterName != null && !filterName.isBlank();
        String pageParam = req.getParameter("page");

        int page = 1;
        int pageSize = 10;

        try {
            if (pageParam != null) {
                page = Integer.parseInt(pageParam);
                if (page < 1) page = 1;
            }
        } catch (NumberFormatException ignored) {
        }

        List<Match> matches = hasFilter
                ? matchDao.findByNameWithPagination(filterName.trim(), page, pageSize)
                : matchDao.getAllWithPagination(page, pageSize);

        int totalMatches = hasFilter
                ? matchDao.countMatchesByName(filterName.trim())
                : matchDao.countAllMatches();

        int totalPages = (int) Math.ceil((double) totalMatches / pageSize);
        if (page > totalPages && totalPages > 0) page = totalPages;



        req.setAttribute("matches", matches);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("filterParam", hasFilter ? "&filter_by_player_name" : "");
        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
