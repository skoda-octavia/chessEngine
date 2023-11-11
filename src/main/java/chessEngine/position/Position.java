package chessEngine.position;
import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.move.Move;
import chessEngine.move.MoveRequestResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "Position",
        uniqueConstraints = @UniqueConstraint(columnNames = {
                "position_code",
                "white_moves",
                "white_king_moved",
                "white_left_rook_moved",
                "white_right_rook_moved",
                "black_king_moved",
                "black_left_rook_moved",
                "black_right_rook_moved",
        })
)

@Data
@NoArgsConstructor
public class Position {

    @JsonIgnore
    @Id
    @SequenceGenerator(
            name = "position_sequence",
            sequenceName = "position_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "position_sequence"
    )
    private Long id;
    @Column(length = 128, nullable = false, name = "position_code")
    private String positionCode;
    @Column(nullable = false, name = "white_moves")
    private boolean whiteMoves;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private List<Move> childMoves;

    @Column(name = "white_king_moved", nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private boolean whiteKingMoved;

    @Column(name = "black_king_moved", nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private boolean blackKingMoved;

    @Column(name = "white_left_rook_moved", nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private boolean whiteLeftRookMoved;

    @Column(name = "white_right_rook_moved", nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private boolean whiteRightRookMoved;

    @Column(name = "black_left_rook_moved", nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private boolean blackLeftRookMoved;

    @Column(name = "black_right_rook_moved", nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private boolean blackRightRookMoved;

//    @JsonIgnore
//    @Transient
//    private EnginePosition enginePosition;
//

    public EnginePosition getEnginePosition(EngineMove parentEngineMove) {
        EnginePosition thisPos = new EnginePosition(
                this.positionCode,
                this.whiteMoves,
                this.whiteKingMoved,
                this.blackKingMoved,
                this.whiteLeftRookMoved,
                this.whiteRightRookMoved,
                this.blackLeftRookMoved,
                this.blackRightRookMoved,
                parentEngineMove);
        thisPos.set();
        return thisPos;
    }

    public Position(Long id, String positionCode, boolean whiteMoves) {
        this.id = id;
        this.positionCode = positionCode;
        this.whiteMoves = whiteMoves;
    }

    public Position(String positionCode, boolean whiteMoves) {
        this.positionCode = positionCode;
        this.whiteMoves = whiteMoves;
    }

    public Position(String positionCode,
                    boolean whiteMoves,
                    boolean whiteKingMoved,
                    boolean blackKingMoved,
                    boolean whiteLeftRookMoved,
                    boolean whiteRightRookMoved,
                    boolean blackLeftRookMoved,
                    boolean blackRightRookMoved) {
        this.positionCode = positionCode;
        this.whiteMoves = whiteMoves;
        this.whiteKingMoved = whiteKingMoved;
        this.blackKingMoved = blackKingMoved;
        this.whiteLeftRookMoved = whiteLeftRookMoved;
        this.whiteRightRookMoved = whiteRightRookMoved;
        this.blackLeftRookMoved = blackLeftRookMoved;
        this.blackRightRookMoved = blackRightRookMoved;
    }
}
