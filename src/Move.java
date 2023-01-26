class Move {
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;
    private Piece piece;

    public Move(int fromX, int fromY, int toX, int toY, Piece piece) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.piece = piece;
    }
    public int getFromX() {
        return fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }
    public Piece getPiece() {
        return piece;
    }
}