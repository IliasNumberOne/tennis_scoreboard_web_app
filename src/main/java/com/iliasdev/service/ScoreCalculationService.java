package com.iliasdev.service;

import com.iliasdev.entity.Match;
import com.iliasdev.model.OngoingMatch;
import com.iliasdev.model.score_model.Point;
import com.iliasdev.model.score_model.Score;
import lombok.Getter;


public class ScoreCalculationService {
    @Getter
    private static final ScoreCalculationService INSTANCE = new ScoreCalculationService();

    private static final FinishedMatchesService finishedMatchesService = FinishedMatchesService.getINSTANCE();
    private boolean isDeuce = false;



    public void countPoint(OngoingMatch match, int scorePlayer) {
        Score player1Score = match.getScorePlayer1();
        Score player2Score = match.getScorePlayer2();

        if(match.isTieBreak()) {
            tieBreakGame(match, scorePlayer);
            return;
        }

        if(isDeuce) {
            deucePoint(match, scorePlayer);
            return;
        }

        if(player1Score.getPoint() == Point.FORTY && player2Score.getPoint() == Point.FORTY) {
            isDeuce = true;

        } else if (player1Score.getPoint() == Point.FORTY && scorePlayer==1) {
            match.getScorePlayer1().nextGame();
            match.resetPoints();

            countGame(match, player1Score, player2Score, scorePlayer);
            return;
        } else if (player2Score.getPoint() == Point.FORTY && scorePlayer==2) {
            match.getScorePlayer2().nextGame();
            match.resetPoints();

            countGame(match, player1Score, player2Score, scorePlayer);
            return;
        }
        nextPoint(match, scorePlayer);

    }

    private void countGame(OngoingMatch match, Score player1Score, Score player2Score, int scorePlayer) {
        if(player1Score.getGame() == 6 && player2Score.getGame() == 6) {
            match.setTieBreak(true);
        } else if (player1Score.getGame() >= 6 && scorePlayer==1 && player1Score.getGame()-player2Score.getGame() >= 2) {
            countSet(match, scorePlayer);
            match.resetGame();
        } else if (player2Score.getGame() >=6 && scorePlayer==2 && player2Score.getGame()-player1Score.getGame() >= 2) {
            countSet(match, scorePlayer);
            match.resetGame();
        }
    }

    private void countSet(OngoingMatch match, int scorePlayer) {
        Score winner = scorePlayer == 1 ? match.getScorePlayer1() : match.getScorePlayer2();
        winner.nextSet();

        if (winner.getSet() == 2) {
            match.setGameOver(true);
            finishedMatchesService.saveMatch(
                    new Match(
                            match.getScorePlayer1().getPlayer(),
                            match.getScorePlayer2().getPlayer(),
                            winner.getPlayer()
                    )
            );
        }
    }

    private void nextPoint(OngoingMatch match, int scorePlayer) {
        if (scorePlayer == 1) {
            match.getScorePlayer1().nextPoint();
        } else if (scorePlayer == 2) {
            match.getScorePlayer2().nextPoint();
        }
    }

    private void deucePoint(OngoingMatch match, int scorePlayer) {
        Score p1 = match.getScorePlayer1();
        Score p2 = match.getScorePlayer2();

        Score current = scorePlayer == 1 ? p1 : p2;
        Score opponent = scorePlayer == 1 ? p2 : p1;

        if (current.getPoint() == Point.ADVANTAGE) {
            current.nextGame();
            match.resetPoints();
            isDeuce = false;
            countGame(match, p1, p2, scorePlayer);
        } else if (opponent.getPoint() == Point.ADVANTAGE) {
            opponent.setPoint(Point.FORTY);
        } else {
            current.setPoint(Point.ADVANTAGE);
        }
    }

    private void tieBreakGame(OngoingMatch match, int scorePlayer) {
        if(scorePlayer == 1){
            match.getScorePlayer1().nextTieBreakPoint();
        } else if (scorePlayer == 2) {
            match.getScorePlayer2().nextTieBreakPoint();
        }

        int pointGap = Math.abs(match.getScorePlayer1().getTieBreakPoint() - match.getScorePlayer2().getTieBreakPoint());

        if(match.getScorePlayer1().getTieBreakPoint() > 6 && pointGap >= 2 && scorePlayer==1) {
            countSet(match, scorePlayer);
            match.resetPoints();
            match.resetGame();
            match.setTieBreak(false);
        } else if (match.getScorePlayer2().getTieBreakPoint() > 6 && pointGap >= 2 && scorePlayer==2) {
            countSet(match, scorePlayer);
            match.resetPoints();
            match.resetGame();
            match.setTieBreak(false);
        }
    }
}
