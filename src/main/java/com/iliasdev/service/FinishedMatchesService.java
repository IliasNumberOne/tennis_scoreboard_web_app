package com.iliasdev.service;

import com.iliasdev.dao.MatchDao;
import com.iliasdev.entity.Match;
import lombok.Getter;

public class FinishedMatchesService {
    @Getter
    private static final FinishedMatchesService INSTANCE = new FinishedMatchesService();
    private static final MatchDao matchDao = MatchDao.getINSTANCE();

    public void saveMatch(Match match) {
        matchDao.save(match);
    }
}
