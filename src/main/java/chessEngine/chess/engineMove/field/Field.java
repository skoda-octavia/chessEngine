package chessEngine.chess.engineMove.field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
public class Field {
    private byte height;
    private byte width;
}
