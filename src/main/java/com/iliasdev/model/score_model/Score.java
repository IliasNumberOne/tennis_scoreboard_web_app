package com.iliasdev.model.score_model;

import com.iliasdev.entity.Player;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Score {
    private Player player;

    private int set = 0;
    private int game = 0;
    private Point point = Point.ZERO;
    private int tieBreakPoint = 0;

    public Score(Player player) {
        this.player = player;
    }

    public void nextPoint() {
        this.point = point.next();
    }

    public void nextGame() {
        this.game = game+1;
    }

    public void nextSet() {
        this.set = set+1;
    }
    public void nextTieBreakPoint() {
        this.tieBreakPoint = tieBreakPoint+1;
    }


}
