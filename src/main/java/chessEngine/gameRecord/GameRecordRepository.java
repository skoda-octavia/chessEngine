package chessEngine.gameRecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRecordRepository extends JpaRepository<GameRecord, Long> {
}
