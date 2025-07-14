package com.iliasdev.service;

import com.iliasdev.dao.PlayerDao;
import com.iliasdev.entity.Player;
import lombok.Getter;

public class NewMatchService {
    @Getter
    private static final NewMatchService INSTANCE = new NewMatchService();
    private static final PlayerDao playerDao = PlayerDao.getINSTANCE();

    public Player getOrCreatePlayer(String playerName) {
        return playerDao.findByName(playerName).orElseGet(() -> playerDao.save(new Player(playerName)));
    }

}
