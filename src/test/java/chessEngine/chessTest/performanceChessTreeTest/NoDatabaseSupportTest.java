package chessEngine.chessTest.performanceChessTreeTest;


public class NoDatabaseSupportTest {

//    @Test
//    public void earlyGameTest() {
//        int depthList[] =  { 4, 5, 6};
//
//        String posCode = "";
//        posCode += "bRbkbBbQbKbBbkbR";
//        posCode += "bPbPbPbPbPbPbPbP";
//        posCode += "                ";
//        posCode += "                ";
//        posCode += "                ";
//        posCode += "                ";
//        posCode += "wPwPwPwPwPwPwPwP";
//        posCode += "wRwkwBwQwKwBwkwR";
//        EnginePosition enginePosition = new EnginePosition(posCode, true);
//        enginePosition.set();
//        JSONObject jsonObject = new JSONObject();
//        for (int depth : depthList) {
//            Node primeNode = new Node(enginePosition);
//            ChessTree chessTree = new ChessTree(primeNode, depth);
//            System.gc();
//            long startTime = System.currentTimeMillis();
//            chessTree.generateTree();
//            long endTime = System.currentTimeMillis();
//            long duration = endTime - startTime;
//            float durationFloat = Float.valueOf(duration);
//            System.out.println("Testing early game for depth: " + depth + " Time: " + durationFloat);
//            jsonObject.put(depth,durationFloat / 1000);
//        }
//        try {
//            FileWriter file = new FileWriter("C:/Użytkownicy/Piotr/output.json");
//            file.write(jsonObject.toJSONString());
//            file.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
