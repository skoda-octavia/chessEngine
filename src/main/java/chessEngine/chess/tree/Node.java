package chessEngine.chess.tree;

import chessEngine.chess.ChildGenerator;
import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Node {
    private final EnginePosition enginePosition;

    private int value;

    private ArrayList<Node> children = new ArrayList<>();

    private EngineMove parentMove;

    public void generateChildrenNodes(int depth) {
        if (!enginePosition.isSet()) {enginePosition.set();}
        if (depth > 0){
            ArrayList<EngineMove> childMoves = enginePosition.possibleLegalMoves();
            for (EngineMove childMove : childMoves) {
                Node childNode = new Node(ChildGenerator.generateChild(this.enginePosition, childMove), childMove);
                children.add(childNode);
                childNode.generateChildrenNodes(depth - 1);
            }
        }

    }

    public Node(EnginePosition enginePosition, EngineMove parentMove) {
        this.enginePosition = enginePosition;
        this.parentMove = parentMove;
    }

    public Node(EnginePosition enginePosition) {
        this.enginePosition = enginePosition;
    }
}
