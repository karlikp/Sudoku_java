package pl.polsl.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "PLAYER")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPLAYER")
    private int id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GameEntity> games;

    public PlayerEntity() {}

    public PlayerEntity(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public List<GameEntity> getGames() { return games; }
    public void setGames(List<GameEntity> games) { this.games = games; }
}
