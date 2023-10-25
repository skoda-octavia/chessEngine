package chessEngine.currentGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentGameRepository extends JpaRepository<CurrentGame, Long> {
}
