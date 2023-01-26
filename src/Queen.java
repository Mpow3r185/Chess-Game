class Queen implements IRuleStrategy {

    @Override
    public boolean canMoveTo(Piece piece, int x, int y) {
        Square toSquare = Board.getInstance().getSquare(x, y);
        if (toSquare.getPiece() != null && toSquare.getPiece().getColor() == piece.getColor()) {
            return false;
        }
        int dy = y - piece.getY();
        int dx = x - piece.getX();
        return (Math.abs(dy) == Math.abs(dx)) || (y == piece.getY() || x == piece.getX());
    }
}
