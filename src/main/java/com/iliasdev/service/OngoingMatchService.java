package com.iliasdev.service;

import com.iliasdev.entity.Player;
import com.iliasdev.model.OngoingMatch;
import lombok.Getter;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchService {
    @Getter
    private static final OngoingMatchService INSTANCE = new OngoingMatchService();
    private final Map<UUID, OngoingMatch> ongoingMatches = new ConcurrentHashMap<>();


    public UUID createMatch(Player player1, Player player2) {
        OngoingMatch ongoingMatch = new OngoingMatch(player1, player2);
        ongoingMatches.put(ongoingMatch.getUuid(), ongoingMatch);
        return ongoingMatch.getUuid();
    }

    public OngoingMatch findById(UUID uuid) {
        return ongoingMatches.get(uuid);
    }
}
