package chessEngine.chess.tree;

import chessEngine.chess.ChildGenerator;
import chessEngine.chess.EnginePosition;
import chessEngine.chess.HeuristicGenerator;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.EngineMoveCode;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Node {
    private EnginePosition enginePosition;

    private int value;

    private ArrayList<Node> children = new ArrayList<>();

    private EngineMove parentMove;

    private int treeDepth;

    public void generateChildrenNodes() {
        generateChildrenNodes(treeDepth);
    }

    public void generateChildrenNodes(int depth) {
        if (!enginePosition.isSet()) {enginePosition.set();}
        if (depth > 0){
            ArrayList<EngineMove> childrenMoves = enginePosition.possibleLegalMoves();
            if (childrenMoves.size() == 0) {
                this.value = enginePosition.generateHeuristicValue();
                this.enginePosition = null;
            }
            for (EngineMove childMove : childrenMoves) {
                Node childNode = new Node(ChildGenerator.generateChild(this.enginePosition, childMove), childMove, treeDepth);
                children.add(childNode);
                childNode.generateChildrenNodes(depth - 1);
            }

            if (depth != treeDepth){
                this.enginePosition = null;
            }
        }
        else {
            this.value = enginePosition.generateHeuristicValue();
            enginePosition = null;
        }
    }

    public EngineMove findOptimalChildMove() {
        return findOptimalChildMove(treeDepth);
    }

    public EngineMove findOptimalChildMove(int depth) {
        if (children.size() == 0) {generateChildrenNodes(depth);}
        int optimalValue = enginePosition.isWhiteMoves() ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Node optimalNode = null;
        Node castlingNode = null;
        boolean maximizingPlayer = !enginePosition.isWhiteMoves();
        for (Node childNode : children) {
            int tempVal = minimax(
                    childNode,
                    depth - 1,
                    Integer.MIN_VALUE,
                    Integer.MAX_VALUE,
                    maximizingPlayer
            );
            if(childNode.getParentMove().getEngineMoveCode().equals(EngineMoveCode.CASTLING)) {
                castlingNode = childNode;
            }
            if (enginePosition.isWhiteMoves() && tempVal > optimalValue) {
                optimalValue = tempVal;
                optimalNode = childNode;
            } else if (!enginePosition.isWhiteMoves() && tempVal < optimalValue) {
                optimalValue = tempVal;
                optimalNode = childNode;
            }
        }
        if (castlingNode != null && Math.abs(optimalValue) < HeuristicGenerator.CASTLING_THRESHOLD) {
            return castlingNode.getParentMove();
        }
        return optimalNode.getParentMove();
    }


    public int minimax(Node node, int depth, int alpha, int beta, boolean maximizingPlayer) {
        if (depth == 0 || node.children.size() == 0) {return node.value;}
        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Node child : node.children) {
                int eval = minimax(child, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Node child : node.children) {
                int eval = minimax(child, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return minEval;
        }
    }

    public Node(EnginePosition enginePosition, EngineMove parentMove, int treeDepth) {
        this.enginePosition = enginePosition;
        this.parentMove = parentMove;
        this.treeDepth = treeDepth;
    }

    public Node(EnginePosition enginePosition, int treeDepth) {
        this.enginePosition = enginePosition;
        this.treeDepth = treeDepth;
    }
}
