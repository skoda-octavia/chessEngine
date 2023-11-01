package chessEngine.chessTest.engine;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EngineControlledFieldTest {

    @Test
    void controlledFieldTest() {
        String posCode = "";
        posCode += "bRbkbBbQ  bBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    bB          ";
        posCode += "  wP            ";
        posCode += "wK          bKwR";
        posCode += "                ";
        posCode += "wPwPwPwP  wPwP  ";
        posCode += "wRwkwBwQ  wBwk  ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        assertTrue(enginePosition.controlledField(new Field((byte)3, (byte)0), PieceColor.WHITE));
        assertTrue(enginePosition.controlledField(new Field((byte)3, (byte)1), PieceColor.WHITE));
        assertTrue(enginePosition.controlledField(new Field((byte)5, (byte)3), PieceColor.WHITE));
        assertTrue(enginePosition.controlledField(new Field((byte)4, (byte)6), PieceColor.WHITE));
        assertTrue(enginePosition.controlledField(new Field((byte)6, (byte)0), PieceColor.WHITE));
        assertTrue(enginePosition.controlledField(new Field((byte)3, (byte)7), PieceColor.WHITE));

        assertTrue(enginePosition.controlledField(new Field((byte)3, (byte)1), PieceColor.BLACK));
        assertTrue(enginePosition.controlledField(new Field((byte)0, (byte)2), PieceColor.BLACK));
        assertTrue(enginePosition.controlledField(new Field((byte)2, (byte)5), PieceColor.BLACK));
        assertTrue(enginePosition.controlledField(new Field((byte)3, (byte)7), PieceColor.BLACK));
        assertTrue(enginePosition.controlledField(new Field((byte)4, (byte)7), PieceColor.BLACK));

        assertFalse(enginePosition.controlledField(new Field((byte)2, (byte)1), PieceColor.WHITE));
        assertFalse(enginePosition.controlledField(new Field((byte)7, (byte)0), PieceColor.WHITE));
        assertFalse(enginePosition.controlledField(new Field((byte)0, (byte)0), PieceColor.WHITE));
        assertFalse(enginePosition.controlledField(new Field((byte)3, (byte)2), PieceColor.WHITE));
    }
}
