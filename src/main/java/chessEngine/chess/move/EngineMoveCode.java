package chessEngine.chess.move;

public enum EngineMoveCode {
    NONE((byte)0),
    ENPASSANT((byte)1),
    KNIGHT((byte)2),
    ROOK((byte)3),
    QUEEN((byte)4),
    BISHOP((byte)5);

    public byte number;

    EngineMoveCode(byte number) {
        this.number = number;
    }
}
