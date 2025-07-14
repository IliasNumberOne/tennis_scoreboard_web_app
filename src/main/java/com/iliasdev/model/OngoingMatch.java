package com.iliasdev.model;

import com.iliasdev.entity.Player;
import com.iliasdev.model.score_model.Point;
import com.iliasdev.model.score_model.Score;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class OngoingMatch {
    private UUID uuid;
    private Score scorePlayer1;
    private Score scorePlayer2;
    private boolean isTieBreak = false;
    private boolean isGameOver = false;

    public OngoingMatch(Player player1, Player player2) {
        this.uuid = UUID.randomUUID();
        this.scorePlayer1 = new Score(player1);
        this.scorePlayer2 = new Score(player2);
    }

    public void resetPoints() {
        this.scorePlayer1.setPoint(Point.ZERO);
        this.scorePlayer2.setPoint(Point.ZERO);
        this.scorePlayer1.setTieBreakPoint(0);
        this.scorePlayer2.setTieBreakPoint(0);
    }

    public void resetGame() {
        this.scorePlayer1.setGame(0);
        this.scorePlayer2.setGame(0);
    }
}
