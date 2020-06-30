package ro.fasttrack.demo.service;

import org.springframework.stereotype.Service;
import ro.fasttrack.demo.domain.Player;
import ro.fasttrack.demo.exceptions.ResourceNotFoundException;
import ro.fasttrack.demo.repository.PlayerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(Integer id) {
        return getOrThrowById(id);
    }

    public Player getPlayerByUsername(String username) {
        return getOrThrowByUsername(username);
    }

    public Player addPlayer(Player player) {
        Player playerToAdd = getPlayerByUsername(player.getUsername());
        return playerRepository.save(playerToAdd);
    }

    public Player updatePlayerById(Integer id, Player player) {
        Player playerToUpdate = getOrThrowById(id);
        playerToUpdate.setUsername(player.getUsername() == null ? playerToUpdate.getUsername() : player.getUsername());
        return playerRepository.save(playerToUpdate);
    }

    public Player updatePlayerByUsername(String username, Player player) {
        Player playerToUpdate = getOrThrowByUsername(username);
        playerToUpdate.setUsername(player.getUsername() == null ? playerToUpdate.getUsername() : player.getUsername());
        return playerRepository.save(playerToUpdate);
    }

    public Player replacePlayerById(Integer id, Player player) {
        Player playerToReplace = getOrThrowById(id);
        playerToReplace.setUsername(player.getUsername());
        playerToReplace.setWins(player.getWins());
        playerToReplace.setLosses(player.getLosses());
        playerToReplace.setDraws(player.getDraws());
        return playerRepository.save(playerToReplace);
    }

    public Player replacePlayerByUsername(String username, Player player) {
        Player playerToReplace = getOrThrowByUsername(username);
        playerToReplace.setUsername(player.getUsername());
        playerToReplace.setWins(player.getWins());
        playerToReplace.setLosses(player.getLosses());
        playerToReplace.setDraws(player.getDraws());
        return playerRepository.save(playerToReplace);
    }

    public Player deletePlayerById(Integer id) {
        Player playerToDelete = getOrThrowById(id);
        playerRepository.delete(playerToDelete);
        return playerToDelete;
    }

    public Player deletePlayerByUsername(String username) {
        Player playerToDelete = getOrThrowByUsername(username);
        playerRepository.delete(playerToDelete);
        return playerToDelete;
    }

    public Map<String, Map<String, String>> getPlayerHistory(String username) {
        Player player = getOrThrowByUsername(username);
        return playerHistoryMap(player);
    }

    private Map<String, Map<String, String>> playerHistoryMap(Player player) {
        Map<String, Map<String, String>> finalMap = new HashMap<>();
        finalMap.put(player.getUsername(), playerResults(player));
        return finalMap;
    }

    private Map<String, String> playerResults(Player player) {
        Map<String, String> results = new HashMap<>();
        results.put("wins", String.valueOf(player.getWins()));
        results.put("losses", String.valueOf(player.getLosses()));
        results.put("draws", String.valueOf(player.getDraws()));
        return results;
    }

    public void addWinToPlayer(Player player) {
        player.addWin();
        playerRepository.save(player);
    }

    public void addLossToPlayer(Player player) {
        player.addLoss();
        playerRepository.save(player);
    }

    public void addDrawToPlayer(Player player) {
        player.addDraw();
        playerRepository.save(player);
    }

    private Player getOrThrowByUsername(String username) {
        return playerRepository.findAll().stream()
                .filter(player -> player.getUsername().equals(username))
                .findFirst()
                .orElse(new Player(username));
    }

    protected Player getOrThrowById(Integer id) {
        return this.playerRepository.findAll().stream()
                .filter(player -> player.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Could not find player with ID " + id));
    }
}