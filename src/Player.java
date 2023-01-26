import java.util.Scanner;
import java.util.regex.Pattern;

class Player {
    private Color color;
    private Scanner scanner;
    public Player(Color color) {
        this.color = color;
        this.scanner = new Scanner(System.in);
    }
    public Color getColor() {
        return color;
    }
    private static int[] convertToIndices(char column, int row) {
        int columnIndex = column - 'a';
        int rowIndex = 8 - row;
        return new int[] {rowIndex, columnIndex};
    }
    public Move getNextMove() {
        System.out.println(color + " player's turn. Enter: ");
        String from = scanner.next();
        String to = scanner.next();

         if(!isLegalMove(from) || !isLegalMove(to)) {
             System.out.println("Invalid move: you must enter from [a-h] and [1-8].");
             getNextMove();
         }
        int[] fromIndices = convertToIndices(from.charAt(0), Integer.parseInt(from.substring(1)));
        int[] fromIndices2 = convertToIndices(to.charAt(0), Integer.parseInt(to.substring(1)));
        int fromX = fromIndices[0];
        int fromY = fromIndices[1];
        int toX = fromIndices2[0];
        int toY = fromIndices2[1];

        Square fromSquare = Board.getInstance().getSquare(fromX, fromY);
        if (fromSquare.getPiece() == null || fromSquare.getPiece().getColor() != color) {
            System.out.println("Invalid move: you must select one of your own pieces.");
            return getNextMove();
        }
        if (!fromSquare.getPiece().canMoveTo(toX, toY)) {
            System.out.println("Invalid move: the selected piece cannot move to the destination square.");
            return getNextMove();
        }

        return new Move(fromX, fromY, toX, toY, fromSquare.getPiece());
    }
    private static boolean isLegalMove(String move) {
        return move.matches("^[a-h][1-8]$");
    }


}
