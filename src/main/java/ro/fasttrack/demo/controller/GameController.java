package ro.fasttrack.demo.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrack.demo.domain.Game;
import ro.fasttrack.demo.domain.Hand;
import ro.fasttrack.demo.service.GameService;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

@RestController
@RequestMapping("games")
public class GameController {
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAllGames(@RequestParam(required = false) String username) {
        if (username == null) {
            return gameService.getAllGames();
        } else {
            return gameService.gamesWithPlayerInvolved(username);
        }
    }

    @GetMapping("{id}")
    public Game getGameById(@PathVariable Integer id) {
        return gameService.getGameById(id);
    }

    @GetMapping("{gameUrl}/summary")
    public SortedMap<String, String> gameSummary(@PathVariable String gameUrl) {
        return gameService.getGameSummary(gameUrl);
    }

    @PostMapping
    public Game addGame(@RequestBody Game newGame) {
        return gameService.addGame(newGame);
    }

    @PutMapping("/{gameUrl}/player/1")
    public Game pickPlayer1Hand(@PathVariable String gameUrl, @RequestBody Hand hand) {
        return gameService.pickHandForPlayer1(gameUrl, hand);
    }

    @PutMapping("/{gameUrl}/player/2")
    public Game pickPlayer2Hand(@PathVariable String gameUrl, @RequestBody Hand hand) {
        return gameService.pickHandForPlayer2(gameUrl, hand);
    }
}
