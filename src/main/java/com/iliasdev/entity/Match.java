package com.iliasdev.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_1", nullable = false)
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player_2", nullable = false)
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner", nullable = false)
    private Player winner;
}
