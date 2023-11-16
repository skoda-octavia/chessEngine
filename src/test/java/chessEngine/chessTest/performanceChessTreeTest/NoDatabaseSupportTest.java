package chessEngine.chessTest.performanceChessTreeTest;


import chessEngine.chess.EnginePosition;
import chessEngine.chess.tree.ChessTree;
import chessEngine.chess.tree.Node;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;

public class NoDatabaseSupportTest {

    @Test
    public void earlyGameTest() {
        int depthList[] =  {2, 3, 4, 5, 6};

        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        JSONObject jsonObject = new JSONObject();
        for (int depth : depthList) {
            Node primeNode = new Node(enginePosition);
            ChessTree chessTree = new ChessTree(primeNode, depth);
            System.out.println("Testing for depth: " + depth);
            long startTime = System.currentTimeMillis();
            chessTree.generateTree();
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            float durationFloat = Float.valueOf(duration);
            jsonObject.put(depth,durationFloat / 1000);
        }
        try {
            FileWriter file = new FileWriter("C:/Users/Piotr/output.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
