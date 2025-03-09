package pl.polsl.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "MOVEMENT")
public class MovementEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDMOVEMENT")
    private int id;

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDGAME", nullable = false)
    @JsonBackReference
    private GameEntity game;

    @Column(name = "MOVEDIGIT", nullable = false)
    private int moveDigit;

    @Column(name = "ROW", nullable = false)
    private int row;
    
    @Column(name = "COLNUMBER", nullable = false)  
    private int colNumber;

    public MovementEntity() {}

    public MovementEntity(GameEntity game, int moveDigit, int row, int colNumber) {
        this.game = game;
        this.moveDigit = moveDigit;
        this.row = row;
        this.colNumber = colNumber;
    }

    public int getId() { return id; }
    public GameEntity getGame() { return game; }
    public void setGame(GameEntity game) { this.game = game; }

    public int getMoveDigit() { return moveDigit; }
    public void setMoveDigit(int moveDigit) { this.moveDigit = moveDigit; }

    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }

    public int getColNumber() { return colNumber; }
    public void setColNumber(int column) { this.colNumber = column; }
}
