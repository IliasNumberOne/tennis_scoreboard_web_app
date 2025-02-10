package com.iliasdev.model;

import com.iliasdev.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class OngoingMatch {
    private UUID uuid;
    private Player player1;
    private Player player2;

    public OngoingMatch(Player player1, Player player2) {
        this.uuid = UUID.randomUUID();
        this.player1 = player1;
        this.player2 = player2;
    }
}
