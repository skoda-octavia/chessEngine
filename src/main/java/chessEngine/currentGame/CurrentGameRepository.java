package chessEngine.currentGame;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

@Repository
public interface CurrentGameRepository extends JpaRepository<CurrentGame, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE CurrentGame c SET c.position = :next_position_id WHERE c.id = :current_game_id")
    void updatePosition(@Param("current_game_id") Long currentGameId,
                        @Param("next_position_id") Long nextPositionId);

    Optional<CurrentGame> findByAccountId(Long accountId);
}