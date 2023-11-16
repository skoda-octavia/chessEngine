package chessEngine.chess.tree;

import lombok.Data;

@Data
public class ChessTree {

    private final Node primeNode;

    private final int depth;

    public void generateTree() {
        primeNode.generateChildrenNodes(depth - 1);
    }

    public ChessTree(Node primeNode, int depth) {
        if (primeNode.getEnginePosition() == null) {
            throw new IllegalStateException("given node has no enginePosition");
        }
        this.primeNode = primeNode;
        this.depth = depth;
    }



}
