class Bishop implements IRuleStrategy {
    Board board = Board.getInstance();
    @Override
    public boolean canMoveTo(Piece piece, int x, int y) {
        Square toSquare = Board.getInstance().getSquare(x, y);
        if (toSquare.getPiece() != null && toSquare.getPiece().getColor() == piece.getColor()) {
            return false;
        }
        int dy = y - piece.getY();
        int dx = x - piece.getX();
        if(Math.abs(dy) == Math.abs(dx)) {
            return true;
        }
        else if(Math.abs(dy) != Math.abs(dx)){
            return false;
        }
        else {
            int dx2 = x > dx ? 1 : -1;
            int dy2 = y > dy ? 1 : -1;
            while (dx != x && dy != y) {
                dx += dx2;
                dy += dy2;
                if (board.getSquare(dx, dy).getPiece() != null) {
                    return false;
                }
            }

        }
        return true;
    }

}
