package chessEngine.chess;

import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.constantMovesPiece.king.BlackKing;
import chessEngine.chess.piece.constantMovesPiece.king.King;
import chessEngine.chess.piece.constantMovesPiece.king.WhiteKing;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;
import chessEngine.chess.piece.infiniteRangePiece.queen.BlackQueen;
import chessEngine.chess.piece.infiniteRangePiece.queen.WhiteQueen;
import chessEngine.chess.piece.pawn.Pawn;
import chessEngine.chess.pieceGenerator.PieceGenerator;
import chessEngine.position.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class EnginePosition {

    private boolean whiteKingMoved;
    private boolean blackKingMoved;
    private boolean whiteLeftRookMoved;
    private boolean whiteRightRookMoved;
    private boolean blackLeftRookMoved;
    private boolean blackRightRookMoved;
    private final String positionCode;
    private boolean whiteMoves;
    private final byte boardHeight = 8;
    private final byte boardWidth = 8;
    private EngineMove parentMove = null;
    private int value = 0;

    private final CastlingOperator castlingOperator;

    private Piece[][] chessBoard = new Piece[this.boardHeight][this.boardWidth];
    private PieceColor[][] colorMap = null;
    private LinkedList<Piece> blackPieces = new LinkedList<>();
    private LinkedList<Piece> whitePieces = new LinkedList<>();
    private WhiteKing whiteKing = null;
    private BlackKing blackKing = null;
    private WhiteQueen whiteQueen = null;
    private BlackQueen blackQueen = null;

    private ArrayList<EngineMove> possibleLegalMoves = null;
    private HashMap<Field, Byte> whiteControls;
    private HashMap<Field, Byte> blackControls;
    private boolean set = false;
    private final HeuristicGenerator heuristicGenerator = new HeuristicGenerator(this);

    public PieceColor[][] getColorMap() {
        if (this.colorMap == null) {
            this.colorMap = this.generateColorMap();
        }
        return this.colorMap;
    }

    private PieceColor[][] generateColorMap() {

        PieceColor[][] colorMap = new PieceColor[this.boardHeight][this.boardWidth];
        for (byte h = 0; h < this.boardHeight; h++) {
            for (byte w = 0; w< this.boardWidth; w++ ) {
                Piece tempPiece = this.chessBoard[h][w];
                if (tempPiece == null) {colorMap[h][w] = PieceColor.NONE;}
                else {colorMap[h][w] = tempPiece.getPieceColor();}
            }
        }
        return colorMap;
    }

    public ArrayList<Piece> checkingPieces(PieceColor pieceColor) {
        LinkedList<Piece> oppositePieces = pieceColor.equals(PieceColor.WHITE) ? this.whitePieces : this.blackPieces;
        Field kingsField = pieceColor.equals(PieceColor.WHITE) ? this.blackKing.getField() : this.whiteKing.getField();
        ArrayList<Piece> checkingPieces = new ArrayList<>();
        Iterator<Piece> iterator = oppositePieces.iterator();
        while (iterator.hasNext()) {
            Piece tempPiece = iterator.next();
            Field tempPieceField = tempPiece.getField();
            HashSet<Field> controlledFields = tempPiece.getControlledFields();
            if (controlledFields.contains(kingsField)) {checkingPieces.add(tempPiece);}
        }
        return checkingPieces;
    }

    public boolean pawnOnField(Field field) {
        Piece piece = chessBoard[field.getHeight()][field.getWidth()];
        if (piece instanceof Pawn) {return true;}
        return false;
    }

    public ArrayList<EngineMove> possibleCastlingMoves(PieceColor pieceColor) {
        return this.castlingOperator.possibleCastlingMoves(pieceColor);
    }

    public Field queensField(PieceColor pieceColor) {
        if (pieceColor.equals(PieceColor.WHITE)) {return this.whiteQueen.getField();}
        else if (pieceColor.equals(PieceColor.BLACK)) {return this.blackQueen.getField();}
        else {throw new IllegalArgumentException("pieceColor is none");}
    }

    public void buildControlFieldMap() {
        HashMap<Field, Byte> whiteControls = new HashMap<>();
        HashMap<Field, Byte> blackControls = new HashMap<>();
        Iterator<Piece> iterator = whitePieces.iterator();
        while (iterator.hasNext()) {
            Piece tempPiece = iterator.next();
            tempPiece.setFieldMap(whiteControls);
        }
        iterator = blackPieces.iterator();
        while (iterator.hasNext()) {
            Piece tempPiece = iterator.next();
            tempPiece.setFieldMap(blackControls);
        }
        this.whiteControls = whiteControls;
        this.blackControls = blackControls;

    }

    public ArrayList<EngineMove> possibleLegalMoves() {
        if (this.possibleLegalMoves != null) {return this.possibleLegalMoves;}
        PieceColor checkingColor = whiteMoves ? PieceColor.BLACK : PieceColor.WHITE;
        ArrayList<Piece> checkingPieces = this.checkingPieces(checkingColor);
        ArrayList<EngineMove> possibleLegalMoves = null;
        switch (checkingPieces.size()) {
            case 0 :
                possibleLegalMoves = this.standardLegalMoves();
                break;
            case 1 :
                possibleLegalMoves = this.singleCheckLegalMoves(checkingPieces);
                break;
            case 2 :
                possibleLegalMoves = this.doubleCheckLegalMoves(checkingPieces);
                break;
            default:
                throw new IllegalArgumentException("incorrect number of checking pieces");
        }
        this.possibleLegalMoves = possibleLegalMoves;
        return possibleLegalMoves;
    }

    private ArrayList<EngineMove> doubleCheckLegalMoves(ArrayList<Piece> checkingPieces) {
        Piece king = whiteMoves ? whiteKing : blackKing;
        ArrayList<EngineMove> possibleKingsInvalidMoves = king.getPossibleMoves();
        HashMap controlledFields = whiteMoves ? this.blackControls : whiteControls;
        ArrayList<EngineMove> possibleKingsMoves = new ArrayList<>();
        for(EngineMove engineMove : possibleKingsInvalidMoves) {
            if (!controlledFields.containsKey(engineMove.getTo())) {possibleKingsMoves.add(engineMove);}
        }
        return possibleKingsMoves;
    }

    private ArrayList<EngineMove> singleCheckLegalMoves(ArrayList<Piece> checkingPieces) {
        Piece checkingPiece = checkingPieces.get(0);
        ArrayList<EngineMove> legalMoves = new ArrayList<>();
        ArrayList<Field> coveringLine = new ArrayList<>();
        Field kingsField = whiteMoves ? whiteKing.getField() : blackKing.getField();
        HashMap enemyFields = whiteMoves ? blackControls : whiteControls;

        if (checkingPiece instanceof InfiniteRangePiece) {
            coveringLine = EngineMove.coveringLine(kingsField, checkingPiece.getField());
        }

        Iterator<Piece> iterator = whiteMoves ? whitePieces.iterator() : blackPieces.iterator();
        while (iterator.hasNext()) {
            Piece tempPiece = iterator.next();
            ArrayList<EngineMove> possibleMoves = tempPiece.getPossibleMoves();

            if (tempPiece.getPinningPiece() != null) {continue;}

            if (tempPiece instanceof King) {
                for (EngineMove move : possibleMoves) {
                    if (!enemyFields.containsKey(move.getTo())) {legalMoves.add(move);}
                }
                continue;
            }

            if (tempPiece instanceof Pawn) {
                for (EngineMove move : possibleMoves) {
                    if(move.getTo().equals(checkingPiece.getField()) &&
                            tempPiece.getField().getWidth() != checkingPiece.getField().getWidth()) {
                        legalMoves.add(move);
                        continue;
                    }
                    if(coveringLine.contains(move.getTo())) {legalMoves.add(move);}
                }
                continue;
            }

            for (EngineMove move : possibleMoves) {
                if(move.getTo().equals(checkingPiece.getField()) || coveringLine.contains(move.getTo())) {
                    legalMoves.add(move);
                }
            }
        }
        return legalMoves;
    }

    private ArrayList<EngineMove> standardLegalMoves() {
        PieceColor movingColor = whiteMoves ? PieceColor.WHITE : PieceColor.BLACK;
        ArrayList<EngineMove> possibleMoves = new ArrayList<>();
        Iterator<Piece> iterator = whiteMoves ? whitePieces.iterator() : blackPieces.iterator();
        HashMap enemyControls = whiteMoves ? blackControls : whiteControls;
        while (iterator.hasNext()) {
            Piece tempPiece = iterator.next();
            ArrayList<EngineMove> possiblePieceMoves = tempPiece.getPossibleMoves();

            if(tempPiece instanceof King) {
                for(EngineMove move : possiblePieceMoves) {
                    if(!enemyControls.containsKey(move.getTo())) {possibleMoves.add(move);}
                }
                continue;
            }

            if(tempPiece.getPinningPiece() != null) {
                Field kingsField = whiteMoves ? whiteKing.getField() : blackKing.getField();
                InfiniteRangePiece pinningPiece = tempPiece.getPinningPiece();
                ArrayList<Field> pinnedLine = EngineMove.coveringLine(kingsField, pinningPiece.getField());
                for(EngineMove move : possiblePieceMoves) {
                    if(pinnedLine.contains(move.getTo()) || move.getTo().equals(pinningPiece.getField())) {
                        possibleMoves.add(move);
                    }
                }
                continue;
            }
            possibleMoves.addAll(possiblePieceMoves);
        }
        return  possibleMoves;
    }

    public void setPiece(Piece tempPiece) {
        if (tempPiece.getPieceColor().equals(PieceColor.WHITE)) {
            this.whitePieces.push(tempPiece);
            if (tempPiece instanceof WhiteKing) {
                if (this.whiteKing != null) {throw new IllegalArgumentException("there are two white kings in posCode");}
                this.whiteKing = (WhiteKing) tempPiece;
            }
            else if (tempPiece instanceof WhiteQueen) {this.whiteQueen = (WhiteQueen) tempPiece;}
        }
        else {
            this.blackPieces.push(tempPiece);
            if (tempPiece instanceof BlackKing) {
                if (this.blackKing != null) {throw new IllegalArgumentException("there are two black kings in posCode");}
                this.blackKing = (BlackKing) tempPiece;
            }
            else if (tempPiece instanceof BlackQueen) {this.blackQueen = (BlackQueen) tempPiece;}
        }
    }

    public void set() {
        int codeIndex = 0;
        for (byte height = 0; height < this.boardHeight; height++) {
            for (byte width = 0; width < this.boardWidth; width++) {
                String pieceCode = "";
                char char1 = positionCode.charAt(codeIndex);
                char char2 = positionCode.charAt(codeIndex + 1);
                pieceCode += String.valueOf(char1) + String.valueOf(char2);
                Piece tempPiece = PieceGenerator.generatePiece(pieceCode, height, width, this);
                if (tempPiece != null) {
                    this.setPiece(tempPiece);
                }
                chessBoard[height][width] = tempPiece;
                codeIndex += 2;
            }
        }
        PieceColor[][] colorMap = generateColorMap();
        for (Piece piece : blackPieces) {piece.setMyPossibilities(colorMap);}
        for (Piece piece : whitePieces) {piece.setMyPossibilities(colorMap);}
        if (whiteKing == null) {throw new IllegalArgumentException("positionCode has no whiteKing: "  + positionCode);}
        if (blackKing == null) {throw new IllegalArgumentException("positionCode has no blackKing: "  + positionCode);}
        buildControlFieldMap();
        whiteKing.setCastlingMoves();
        blackKing.setCastlingMoves();
        possibleLegalMoves();
        this.set = true;
    }

    public boolean controlledField(Field field, PieceColor by) {
        HashMap controlledMap = by.equals(PieceColor.WHITE) ? whiteControls : blackControls;
        if (controlledMap.containsKey(field)) {return true;}
        return false;
    }

    public boolean occupiedField(Field field) {
        if (this.chessBoard[field.getHeight()][field.getWidth()] != null) {return true;}
        return false;
    }

    public ArrayList<EnginePosition> getChildren() {
        if(!this.set) {set();}
        return null;
    }

    public EngineMove generateRandomMove() {
        if (!this.set) {this.set();}
        ArrayList<EngineMove> legalMoves = this.possibleLegalMoves();
        if (legalMoves == null || legalMoves.size() == 0) {
            throw new IllegalStateException("Game over or error while generating possible moves");
        }
        Random random = new Random();
        int randomIdx = random.nextInt(legalMoves.size());
        return legalMoves.get(randomIdx);
    }

    public int generateHeuristicValue() {
        if (!set) {this.set();}
        return heuristicGenerator.generate();
    }


    public EnginePosition(String positionCode,
                          boolean whiteMoves,
                          boolean whiteKingMoved,
                          boolean blackKingMoved,
                          boolean whiteLeftRookMoved,
                          boolean whiteRightRookMoved,
                          boolean blackLeftRookMoved,
                          boolean blackRightRookMoved,
                          EngineMove parentMove) {
        this.positionCode = positionCode;
        this.whiteMoves = whiteMoves;
        this.whiteKingMoved = whiteKingMoved;
        this.blackKingMoved = blackKingMoved;
        this.whiteLeftRookMoved = whiteLeftRookMoved;
        this.whiteRightRookMoved = whiteRightRookMoved;
        this.blackLeftRookMoved = blackLeftRookMoved;
        this.blackRightRookMoved = blackRightRookMoved;
        this.castlingOperator = new CastlingOperator(this);
        this.parentMove = parentMove;
    }

    public EnginePosition(String positionCode, boolean whiteMoves) {
        if (positionCode.length() != this.boardHeight * this.boardWidth * 2) {
            String message = "Incorrect len of positionCode: " + positionCode.length();
            throw new IllegalArgumentException(message);
        }
        this.positionCode = positionCode;
        this.whiteMoves = whiteMoves;
        this.castlingOperator = new CastlingOperator(this);
    }

    public Position getPosition() {
        return new Position(
            this.positionCode,
            this.whiteMoves,
            this.whiteKingMoved,
            this.blackKingMoved,
            this.whiteLeftRookMoved,
            this.whiteRightRookMoved,
            this.blackLeftRookMoved,
            this.blackRightRookMoved);
    }


}
