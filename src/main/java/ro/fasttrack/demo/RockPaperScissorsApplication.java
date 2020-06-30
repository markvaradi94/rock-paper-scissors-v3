package ro.fasttrack.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fasttrack.demo.domain.Game;
import ro.fasttrack.demo.domain.Player;
import ro.fasttrack.demo.service.GameService;
import ro.fasttrack.demo.service.PlayerService;

@SpringBootApplication
public class RockPaperScissorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RockPaperScissorsApplication.class, args);
    }

    @Bean
    CommandLineRunner atStartup(PlayerService playerService, GameService gameService) {
        return args -> {
            Player mark = playerService.addPlayer(new Player("Mark"));
            Player john = playerService.addPlayer(new Player("John"));

            gameService.addGame(new Game(mark, john));
        };
    }
}
