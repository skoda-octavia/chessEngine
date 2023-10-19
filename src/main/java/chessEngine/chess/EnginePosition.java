package chessEngine.chess;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnginePosition {
    private EnginePosition parentPosition = null;
    private int value = 0;
    private String positionCode = "";


}
