import javafx.scene.layout.Pane;

public class Board {
    private Square[][] board;
    private Pane boardPane;

    public Board(Pane pane) {
        this.boardPane = pane;
        this.board = new Square[14][26];
        for (int i = 0; i < board.length; i++) {
            for (int t = 0; t < board[0].length; t++) {
                Square newSquare = new Square(i, t);
                board[i][t] = newSquare;
                pane.getChildren().addAll(newSquare.getShape());
            }
        }
    }

    public Square[][] getBoard() {
        return this.board;
    }

    public boolean gameOver() {
        for (int i = 0; i < 14; i++) {
            if (board[i][1].isOccupied()) {
                return true;
            }
        }
        return false;
    }

    public void clearRows() {
        for (int i = 0; i < 26; i++) {
            boolean shouldClear = true;
            for (int t = 1; t < 13; t++) {
                if (!board[t][i].isOccupied()) {
                    shouldClear = false;
                    break;
                }
            }
            if (shouldClear) {
                for (int y = 0; y < board.length; y++) {
                    for (int t = 0; t < board[0].length; t++) {
                        this.boardPane.getChildren().remove(board[y][t].getShape());
                    }
                }
                for (int t = 1; t < 13; t++) {
                    for (int x = i; x > 1; x--) {
                        board[t][x] = board[t][x - 1];
                        board[t][x - 1].getShape().setY(board[t][x - 1].getShape().getY() + 25);
                    }
                    board[t][1] = new Square(t, 1);
                }
                for (int y = 0; y < board.length; y++) {
                    for (int t = 0; t < board[0].length; t++) {
                        this.boardPane.getChildren().add(board[y][t].getShape());
                    }
                }
                i--;
            }
        }
    }
}