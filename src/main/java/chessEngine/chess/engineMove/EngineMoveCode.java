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

    public int getValue() {
        return number;
    }

    public static EngineMoveCode fromInt(int number) {
        for (EngineMoveCode enumValue : EngineMoveCode.values()) {
            if (enumValue.getValue() == number) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Illegal value: " + number);
    }
}

