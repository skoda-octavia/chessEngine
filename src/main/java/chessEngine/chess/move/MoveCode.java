package chessEngine.chess.move;

public enum MoveCode {
    NONE((byte)0),
    ENPASSANT((byte)1),
    KNIGHT((byte)2),
    ROOK((byte)3),
    QUEEN((byte)4),
    BISHOP((byte)5);

    public byte number;

    MoveCode(byte number) {
        this.number = number;
    }
}
