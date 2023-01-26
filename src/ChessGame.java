class ChessGame {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;

    public ChessGame() {
        this.board = Board.getInstance();
        this.whitePlayer = new Player(Color.WHITE);
        this.blackPlayer = new Player(Color.BLACK);
    }

    public void run() {
        board.initialize();
        while (true) {
            System.out.print("   a   ");
            for (int i = 98; i < 105; i++) {
                System.out.print((char) i + "   ");
            }
            System.out.println();
            for (int i = 0; i < 8; i++) {
                System.out.print(8-i);
                for (int j = 0; j < 8; j++) {
                    Square square = board.getSquare(i, j);
                    if (square.getPiece() == null) {
                        System.out.print("  _ ");
                    } else {
                        System.out.print("  " + square.getPiece().getType().toString().substring(0,2) + "");
                    }
                }
                System.out.println();
            }
            Player currentPlayer = board.getCurrentPlayer() == Color.WHITE ? whitePlayer : blackPlayer;
            Move move = currentPlayer.getNextMove();
            while (move == null) {
                System.out.println("Invalid move, try again.");
                move = currentPlayer.getNextMove();
            }

            board.makeMove(move);
            if (!board.isCheckmate()) {
                System.out.println(currentPlayer.getColor() + " player wins!");
                break;
            }
        }
    }
}
