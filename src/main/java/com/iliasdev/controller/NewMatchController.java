package com.iliasdev.controller;

import com.iliasdev.dao.MatchDao;
import com.iliasdev.dto.NewMatchDTO;
import com.iliasdev.entity.Match;
import com.iliasdev.entity.Player;
import com.iliasdev.service.NewMatchService;
import com.iliasdev.service.OngoingMatchService;
import com.iliasdev.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {
    private static final NewMatchService newMatchService = NewMatchService.getINSTANCE();
    private static final OngoingMatchService ongoingMatchService = OngoingMatchService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerOneName = req.getParameter("playerOne");
        String playerTwoName = req.getParameter("playerTwo");

        NewMatchDTO newMatchDTO = new NewMatchDTO(playerOneName, playerTwoName);
        ValidationUtil.validate(newMatchDTO);

        Player player1 = newMatchService.getOrCreatePlayer(newMatchDTO.getPlayerOneName());
        Player player2 = newMatchService.getOrCreatePlayer(newMatchDTO.getPlayerTwoName());
        UUID uuid = ongoingMatchService.createMatch(player1, player2);


        resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + uuid);

    }
}
