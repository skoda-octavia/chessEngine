package chessEngine.currentGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CurrentGameRepository extends JpaRepository<CurrentGame, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE CurrentGame c SET c.position = :next_position_id WHERE c.id = :current_game_id")
    void updatePosition(@Param("current_game_id") Long currentGameId,
                        @Param("next_position_id") Long nextPositionId);

    @Modifying
    @Query("UPDATE CurrentGame cg SET cg.whiteKingMoved = true")
    void setWhiteKingMovedTrue();

    @Modifying
    @Query("UPDATE CurrentGame cg SET cg.blackKingMoved = true")
    void setBlackKingMovedTrue();

    @Modifying
    @Query("UPDATE CurrentGame cg SET cg.whiteLeftRookMoved = true")
    void setWhiteLeftRookMovedTrue();

    @Modifying
    @Query("UPDATE CurrentGame cg SET cg.whiteRightRookMoved = true")
    void setWhiteRightRookMovedTrue();

    @Modifying
    @Query("UPDATE CurrentGame cg SET cg.blackLeftRookMoved = true")
    void setBlackLeftRookMovedTrue();

    @Modifying
    @Query("UPDATE CurrentGame cg SET cg.blackRightRookMoved = true")
    void setBlackRightRookMovedTrue();

    void deleteById(Long id);

    void delete(CurrentGame currentGame);
}