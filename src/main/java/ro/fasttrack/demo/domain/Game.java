package ro.fasttrack.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @NonNull
    private Player player1;

    @OneToOne
    @NonNull
    private Player player2;

    @NonNull
    private String gameUrl;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @Enumerated(EnumType.STRING)
    private Hand player1Hand;

    @Enumerated(EnumType.STRING)
    private Hand player2Hand;

    public Game(Player player1, Player player2, String gameUrl, GameStatus gameStatus,
                Hand player1Hand, Hand player2Hand) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameUrl = gameUrl;
        this.gameStatus = gameStatus;
        this.player1Hand = player1Hand;
        this.player2Hand = player2Hand;
    }

    public Game(Player player1, Player player2, String gameUrl) {
        this(player1, player2, gameUrl, GameStatus.WAITING_FOR_PLAYER, Hand.NONE, Hand.NONE);
    }

    public Game(Player player1, Player player2, GameStatus gameStatus, Hand player1Hand, Hand player2Hand) {
        this(player1, player2, null, gameStatus, player1Hand, player2Hand);
    }

    public Game(Player player1, Player player2) {
        this(player1, player2, null, GameStatus.WAITING_FOR_PLAYER, Hand.NONE, Hand.NONE);
    }

    public Game(Player player1) {
        this(player1, new Player("No player set"));
    }

    public Game(Player player1, String gameUrl) {
        this(player1, new Player("No player set"), gameUrl);
    }
}
