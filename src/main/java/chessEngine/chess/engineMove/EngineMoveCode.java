package chessEngine.chess.engineMove;

public enum EngineMoveCode {
    NONE(0),
    ENPASSANT(1),
    KNIGHT(2),
    ROOK(3),
    QUEEN(4),
    BISHOP(5),
    CASTLING(6);

    public int number;

    EngineMoveCode(int number) {
        this.number = number;
    }
}
