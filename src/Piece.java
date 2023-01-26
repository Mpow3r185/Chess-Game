
class Piece {
    protected int x;
    protected int y;
    protected Color color;
    protected Type type;
    protected IRuleStrategy rule;


    public Piece(Type type, Color color,int x, int y) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.type = type;

        switch (type) {
            case PAWN:
                this.rule = new Pawn();
                break;
            case ROOK:
                this.rule = new Rook();
                break;
            case KNIGHT:
                this.rule = new Knight();
                break;
            case BISHOP:
                this.rule = new Bishop();
                break;
            case QUEEN:
                this.rule = new Queen();
                break;
            case KING:
                this.rule = new King();
                break;
        }
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
    public IRuleStrategy getRule() {
        return rule;
    }

    public boolean canMoveTo(int x, int y) {
        return rule.canMoveTo(this, x, y);
    }
    static class PieceFactory {
        public static Piece createPiece(Type type, Color color, int x, int y) {
            return new Piece(type, color, x, y);
        }
    }

}
