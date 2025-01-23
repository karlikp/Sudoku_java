package pl.polsl.entities;

import jakarta.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String difficultyLevel;

    @Column
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    public Game() {
    }

    public Game(String difficultyLevel, Integer score, Player player) {
        this.difficultyLevel = difficultyLevel;
        this.score = score;
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
