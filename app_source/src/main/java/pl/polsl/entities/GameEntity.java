package pl.polsl.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MovementEntity> movements = new ArrayList<>();

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
    
    public List<MovementEntity> getMovements() { return movements; }
    public void setMovements(List<MovementEntity> movements) { this.movements = movements; }

    public void addMovement(MovementEntity movement) {
        movements.add(movement);
        movement.setGame(this);
    }

    public void removeMovement(MovementEntity movement) {
        movements.remove(movement);
        movement.setGame(null);
    }
}
