package pl.polsl.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "GAME")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDGAME")
    private int id;

    @Column(name = "DIFFICULTYLEVEL", nullable = false, length = 20)
    private String difficultyLevel;

    @ManyToOne
    @JoinColumn(name = "IDPLAYER", nullable = false)
    private PlayerEntity player;

    public GameEntity() {}

    public GameEntity(String difficultyLevel, PlayerEntity player) {
        this.difficultyLevel = difficultyLevel;
        this.player = player;
    }

    public int getId() { return id; }
    public String getDifficultyLevel() { return difficultyLevel; }
    public void setDifficultyLevel(String difficultyLevel) { this.difficultyLevel = difficultyLevel; }
    public PlayerEntity getPlayer() { return player; }
    public void setPlayer(PlayerEntity player) { this.player = player; }
}
