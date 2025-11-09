package com.iliasdev.controller;

import com.iliasdev.dao.MatchDao;
import com.iliasdev.dao.PlayerDao;
import com.iliasdev.entity.Match;
import com.iliasdev.entity.Player;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.HashMap;
import java.util.Map;

@WebListener
public class DatabaseInitializer implements ServletContextListener {
    private final MatchDao matchDao = MatchDao.getINSTANCE();
    private final PlayerDao playerDao = PlayerDao.getINSTANCE();
    private final Map<String, Player> players = new HashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Создаём игроков
        createPlayers("Ilias", "Adahan", "Samat", "Asan", "Ismail", "Sooron", "Askat", "Esen",
                "Tilek", "Chyngyz", "Ruslan", "Ulan", "Bakyt", "Emir", "Ali", "Bektur",
                "Joldosh", "Ermek", "Daniyar", "Anvar");

        // Сохраняем матчи
        matchDao.save(new Match(p("Ilias"), p("Adahan"), p("Ilias")));
        matchDao.save(new Match(p("Samat"), p("Asan"), p("Asan")));
        matchDao.save(new Match(p("Ismail"), p("Sooron"), p("Ismail")));
        matchDao.save(new Match(p("Askat"), p("Esen"), p("Askat")));
        matchDao.save(new Match(p("Tilek"), p("Chyngyz"), p("Chyngyz")));
        matchDao.save(new Match(p("Ruslan"), p("Ulan"), p("Ruslan")));
        matchDao.save(new Match(p("Bakyt"), p("Emir"), p("Emir")));
        matchDao.save(new Match(p("Ali"), p("Bektur"), p("Bektur")));
        matchDao.save(new Match(p("Joldosh"), p("Ermek"), p("Ermek")));
        matchDao.save(new Match(p("Daniyar"), p("Anvar"), p("Daniyar")));
        matchDao.save(new Match(p("Ilias"), p("Samat"), p("Samat")));
        matchDao.save(new Match(p("Adahan"), p("Ismail"), p("Ismail")));
        matchDao.save(new Match(p("Esen"), p("Sooron"), p("Esen")));
        matchDao.save(new Match(p("Tilek"), p("Ruslan"), p("Tilek")));
        matchDao.save(new Match(p("Ulan"), p("Bakyt"), p("Bakyt")));
        matchDao.save(new Match(p("Emir"), p("Ali"), p("Emir")));
        matchDao.save(new Match(p("Bektur"), p("Joldosh"), p("Bektur")));
        matchDao.save(new Match(p("Ermek"), p("Daniyar"), p("Ermek")));
        matchDao.save(new Match(p("Anvar"), p("Ilias"), p("Ilias")));
        matchDao.save(new Match(p("Adahan"), p("Chyngyz"), p("Chyngyz")));
        matchDao.save(new Match(p("Daniyar"), p("Anvar"), p("Daniyar")));
        matchDao.save(new Match(p("Ilias"), p("Samat"), p("Samat")));
        matchDao.save(new Match(p("Adahan"), p("Ismail"), p("Ismail")));
        matchDao.save(new Match(p("Esen"), p("Sooron"), p("Esen")));
        matchDao.save(new Match(p("Tilek"), p("Ruslan"), p("Tilek")));
        matchDao.save(new Match(p("Ulan"), p("Bakyt"), p("Bakyt")));
        matchDao.save(new Match(p("Emir"), p("Ali"), p("Emir")));
        matchDao.save(new Match(p("Bektur"), p("Joldosh"), p("Bektur")));
        matchDao.save(new Match(p("Ermek"), p("Daniyar"), p("Ermek")));
        matchDao.save(new Match(p("Anvar"), p("Ilias"), p("Ilias")));
        matchDao.save(new Match(p("Adahan"), p("Chyngyz"), p("Chyngyz")));
        matchDao.save(new Match(p("Daniyar"), p("Anvar"), p("Daniyar")));
        matchDao.save(new Match(p("Ilias"), p("Samat"), p("Samat")));
        matchDao.save(new Match(p("Adahan"), p("Ismail"), p("Ismail")));
        matchDao.save(new Match(p("Esen"), p("Sooron"), p("Esen")));
        matchDao.save(new Match(p("Tilek"), p("Ruslan"), p("Tilek")));
        matchDao.save(new Match(p("Ulan"), p("Bakyt"), p("Bakyt")));
        matchDao.save(new Match(p("Emir"), p("Ali"), p("Emir")));
        matchDao.save(new Match(p("Bektur"), p("Joldosh"), p("Bektur")));
        matchDao.save(new Match(p("Ermek"), p("Daniyar"), p("Ermek")));
        matchDao.save(new Match(p("Anvar"), p("Ilias"), p("Ilias")));
        matchDao.save(new Match(p("Adahan"), p("Chyngyz"), p("Chyngyz")));
        matchDao.save(new Match(p("Ulan"), p("Bakyt"), p("Bakyt")));
        matchDao.save(new Match(p("Emir"), p("Ali"), p("Emir")));
        matchDao.save(new Match(p("Bektur"), p("Joldosh"), p("Bektur")));
        matchDao.save(new Match(p("Ermek"), p("Daniyar"), p("Ermek")));
        matchDao.save(new Match(p("Anvar"), p("Ilias"), p("Ilias")));
        matchDao.save(new Match(p("Adahan"), p("Chyngyz"), p("Chyngyz")));
    }

    private void createPlayers(String... names) {
        for (String name : names) {
            Player player = new Player(name);
            playerDao.save(player);
            players.put(name, player);
        }
    }

    private Player p(String name) {
        return players.get(name);
    }
}
