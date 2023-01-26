class Pawn implements IRuleStrategy {
    Board board = Board.getInstance();
    @Override
    public boolean canMoveTo(Piece piece, int x, int y) {
        Square toSquare = Board.getInstance().getSquare(x, y);
        if(Math.abs(x-piece.getX()) == 2 && (piece.getX()==1 || piece.getX()==6)){
            return true;
        }
        else if(Math.abs(x-piece.getX()) == 1 && (piece.getX()==1 || piece.getX()==6)){
            return true;
        }
        else if(Math.abs(x-piece.getX()) == 1 && (piece.getX()!=1 || piece.getX()!=6)){
            return true;
        }
        else if(Math.abs(x-piece.getX()) == 2 && (piece.getX()!=1 || piece.getX()!=6)) {
            return false;
        }
        if (toSquare.getPiece() != null && toSquare.getPiece().getColor() == piece.getColor()) {
            return false;
        }
        if (piece.getColor() == Color.WHITE) {
            if (y == piece.getY() && x == piece.getX() + 1) {
                return true;
            }
            else if (Math.abs(x - piece.getX()) == 1 && y == piece.getY() + 1) {
                Square enemySquare = board.getSquare(x, y);
                Piece enemyPiece = enemySquare.getPiece();
                if (enemyPiece != null && enemyPiece.getColor() != piece.getColor()) {
                    enemySquare.setPiece(null);
                    Square pawnSquare = board.getSquare(x, y);
                    pawnSquare.setPiece(piece);
                    return false;
                }
                return true;
            }
        } else {
            if (y == piece.getY() && x == piece.getX() - 1) {
                return true;
            } else if (Math.abs(y - piece.getY()) == 1 && x == piece.getX() - 1) {
                Square enemySquare = board.getSquare(x, y);
                Piece enemyPiece = enemySquare.getPiece();
                if (enemyPiece != null && enemyPiece.getColor() != piece.getColor()) {
                    enemySquare.setPiece(null);
                    Square pawnSquare = board.getSquare(x, y);
                    pawnSquare.setPiece(piece);
                }
                return true;
            }
        }
        return false;
    }
}