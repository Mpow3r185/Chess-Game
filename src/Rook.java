class Rook implements IRuleStrategy {
    @Override
    public boolean canMoveTo(Piece piece, int x, int y) {
        Square toSquare = Board.getInstance().getSquare(x, y);
        if (toSquare.getPiece() != null && toSquare.getPiece().getColor() == piece.getColor()) {
            return false;
        }
        else
         return y == piece.getY() || x == piece.getX() ;
    }
}