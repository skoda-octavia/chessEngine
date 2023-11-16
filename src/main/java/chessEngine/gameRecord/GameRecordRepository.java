package chessEngine.gameRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface GameRecordRepository extends JpaRepository<GameRecord, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE game_record SET game_code = CONCAT(game_code, :suffix) WHERE id = :id", nativeQuery = true)
    void extendGameCodeById(@Param("id") Long id,
                            @Param("suffix") String suffix);


    @Transactional
    @Modifying
    @Query("UPDATE GameRecord gr SET gr.finished = false WHERE gr.id = :id")
    void updateFinishedToFalse(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE GameRecord gr SET gr.finishedAt = :finishedAt WHERE gr.id = :id")
    void updateFinishedAt(Long id, LocalDateTime finishedAt);

}
