package chessEngine.chessTest.engine;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashMapTest {


    @Test
    void controlledFieldTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    wQ    wR    ";
        posCode += "                ";
        posCode += "                ";
        posCode += "      bR  bB    ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        enginePosition.buildControlFieldMap();
        HashMap whiteMap = enginePosition.getWhiteControls();
        HashMap blackMap = enginePosition.getBlackControls();
        assertEquals(42, whiteMap.size());
        assertEquals(35, blackMap.size());

        assertEquals((byte)4, whiteMap.get(new Field((byte)5, (byte)2)));
        assertEquals((byte)1, whiteMap.get(new Field((byte)7, (byte)4)));
        assertEquals((byte)1, whiteMap.get(new Field((byte)2, (byte)2)));
        assertEquals((byte)5, whiteMap.get(new Field((byte)5, (byte)5)));

        assertEquals((byte)1, blackMap.get(new Field((byte)0, (byte)1)));
        assertEquals((byte)2, blackMap.get(new Field((byte)3, (byte)3)));
        assertEquals((byte)3, blackMap.get(new Field((byte)2, (byte)3)));
        assertEquals((byte)0, blackMap.getOrDefault(new Field((byte)4, (byte)0), (byte)0));

    }

}
