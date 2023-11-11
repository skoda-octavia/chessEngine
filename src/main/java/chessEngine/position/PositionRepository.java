package chessEngine.position;

import chessEngine.chess.EnginePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    @Query("SELECT p FROM Position p WHERE " +
            "p.whiteKingMoved = :whiteKingMoved " +
            "AND p.blackKingMoved = :blackKingMoved " +
            "AND p.whiteLeftRookMoved = :whiteLeftRookMoved " +
            "AND p.whiteRightRookMoved = :whiteRightRookMoved " +
            "AND p.blackLeftRookMoved = :blackLeftRookMoved " +
            "AND p.blackRightRookMoved = :blackRightRookMoved " +
            "AND p.positionCode = :positionCode " +
            "AND p.whiteMoves = :whiteMoves")
    Optional<Position> findByPositionAttributes(
            @Param("whiteKingMoved") boolean whiteKingMoved,
            @Param("blackKingMoved") boolean blackKingMoved,
            @Param("whiteLeftRookMoved") boolean whiteLeftRookMoved,
            @Param("whiteRightRookMoved") boolean whiteRightRookMoved,
            @Param("blackLeftRookMoved") boolean blackLeftRookMoved,
            @Param("blackRightRookMoved") boolean blackRightRookMoved,
            @Param("positionCode") String positionCode,
            @Param("whiteMoves") boolean whiteMoves);

}
