package chessEngine.currentGame;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentGameResponse {
    private String positionCode;
    private int status;
    private boolean whiteMoves;
}
