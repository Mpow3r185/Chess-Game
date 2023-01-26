class King implements IRuleStrategy {
    @Override
    public boolean canMoveTo(Piece piece, int x, int y) {
        Square toSquare = Board.getInstance().getSquare(x, y);
        if (toSquare.getPiece() != null && toSquare.getPiece().getColor() == piece.getColor()) {
            return false;
        }
        int dy = Math.abs(y - piece.getY());
        int dx = Math.abs(x - piece.getX());
        return (dy <= 1 && dx <= 1);
    }
}
