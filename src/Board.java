                class Board {
                    private static Board boardInstance = new Board();
                    private Square[][] squares;
                    private Color currentPlayer;
                    private Board() {
                        this.squares = new Square[8][8];
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                this.squares[i][j] = new Square(i, j, null);
                            }
                        }
                        initialize();
                        this.currentPlayer = Color.WHITE;
                    }
                    public static Board getInstance() {
                        return boardInstance;
                    }
                    public void initialize() {
                        for (int i = 0; i < 8; i++) {
                            squares[1][i] = new Square(1, i, Piece.PieceFactory.createPiece(Type.PAWN, Color.WHITE, 1, i));
                            squares[6][i] = new Square(6, i, Piece.PieceFactory.createPiece(Type.PAWN, Color.BLACK, 6, i));
                        }
                        squares[0][0] = new Square(0, 0, Piece.PieceFactory.createPiece(Type.ROOK, Color.WHITE, 0, 0));
                        squares[0][1] = new Square(0, 1, Piece.PieceFactory.createPiece(Type.KNIGHT, Color.WHITE, 0, 1));
                        squares[0][2] = new Square(0, 2, Piece.PieceFactory.createPiece(Type.BISHOP, Color.WHITE, 0, 2));
                        squares[0][3] = new Square(0, 3, Piece.PieceFactory.createPiece(Type.QUEEN, Color.WHITE, 0, 3));
                        squares[0][4] = new Square(0, 4, Piece.PieceFactory.createPiece(Type.KING, Color.WHITE, 0, 4));
                        squares[0][5] = new Square(0, 5, Piece.PieceFactory.createPiece(Type.BISHOP, Color.WHITE, 0, 5));
                        squares[0][6] = new Square(0, 6, Piece.PieceFactory.createPiece(Type.KNIGHT, Color.WHITE, 0, 6));
                        squares[0][7] = new Square(0, 7, Piece.PieceFactory.createPiece(Type.ROOK, Color.WHITE, 0, 7));

                        squares[7][0] = new Square(7, 0, Piece.PieceFactory.createPiece(Type.ROOK, Color.BLACK, 7, 0));
                        squares[7][1] = new Square(7, 1, Piece.PieceFactory.createPiece(Type.KNIGHT, Color.BLACK, 7, 1));
                        squares[7][2] = new Square(7, 2, Piece.PieceFactory.createPiece(Type.BISHOP, Color.BLACK, 7, 2));
                        squares[7][3] = new Square(7, 3, Piece.PieceFactory.createPiece(Type.QUEEN, Color.BLACK, 7, 3));
                        squares[7][4] = new Square(7, 4, Piece.PieceFactory.createPiece(Type.KING, Color.BLACK, 7, 4));
                        squares[7][5] = new Square(7, 5, Piece.PieceFactory.createPiece(Type.BISHOP, Color.BLACK, 7, 5));
                        squares[7][6] = new Square(7, 6, Piece.PieceFactory.createPiece(Type.KNIGHT, Color.BLACK, 7, 6));
                        squares[7][7] = new Square(7, 7, Piece.PieceFactory.createPiece(Type.ROOK, Color.BLACK, 7, 7));
                    }
                    public Square getSquare(int x, int y) {
                        return squares[x][y];
                    }
                    public void makeMove(Move move) {
                        Square fromSquare = getSquare(move.getFromX(), move.getFromY());
                        Square toSquare = getSquare(move.getToX(), move.getToY());
                        Piece piece = fromSquare.getPiece();
                        if (piece != null && piece.getColor() == currentPlayer) {
                            if (piece.getRule().canMoveTo(piece, toSquare.getX(), toSquare.getY())) {
                                toSquare.setPiece(piece);
                                piece.setX(toSquare.getX());
                                piece.setY(toSquare.getY());
                                fromSquare.setPiece(null);
                                switchPlayer();
                            } else {
                                System.out.println("Invalid move");
                            }
                        } else {
                            System.out.println("Invalid move");
                        }

                    }
                    public boolean isCheckmate() {
                        Square kingSquare = null;
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                Square square = squares[i][j];
                                Piece piece = square.getPiece();
                                if (piece != null && piece.getType() == Type.KING && piece.getColor() == currentPlayer) {
                                    kingSquare = square;
                                    break;
                                }
                            }
                        }
                        if (kingSquare == null) {
                            return false;
                        }
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                Square square = squares[i][j];
                                Piece piece = square.getPiece();
                                if (piece != null && piece.getColor() != currentPlayer) {
                                    if (piece.getRule().canMoveTo(piece, kingSquare.getX(), kingSquare.getY())) {
                                        return true;
                                    }
                                }
                            }
                        }
                        int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
                        int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
                        for (int i = 0; i < 8; i++) {
                            int x = kingSquare.getX() + dx[i];
                            int y = kingSquare.getY() + dy[i];
                            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                                Square square = squares[x][y];
                                Piece piece = square.getPiece();
                                if (piece == null || piece.getColor() != currentPlayer) {
                                    if (!isKingInCheckAfterMove(kingSquare, square)) {
                                        return false;
                                    }
                                }
                            }
                        }
                        return true;
                    }
                    private boolean isKingInCheckAfterMove(Square fromSquare, Square toSquare) {
                        Square[][] savedSquares = new Square[8][8];
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                savedSquares[i][j] = squares[i][j];
                            }
                        }
                        Color savedCurrentPlayer = currentPlayer;
                        Piece piece = fromSquare.getPiece();
                        toSquare.setPiece(piece);
                        piece.setX(toSquare.getX());
                        piece.setY(toSquare.getY());
                        fromSquare.setPiece(null);
                        switchPlayer();
                        boolean result = isCheckmate();
                        currentPlayer = savedCurrentPlayer;
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                squares[i][j] = savedSquares[i][j];
                            }
                        }
                        return result;
                    }
                    public void switchPlayer() {
                        this.currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
                    }
                    public Color getCurrentPlayer() {
                        return currentPlayer;
                    }
}
