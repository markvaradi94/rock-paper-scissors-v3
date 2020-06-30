package ro.fasttrack.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrack.demo.domain.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
