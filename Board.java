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
            for (int o = 1; o < 13; o++) {
                if (!board[o][i].isOccupied()) {
                    shouldClear = false;
                    break;
                }
            }
            if (shouldClear) {
                for (int k = 0; k < board.length; k++) {
                    for (int j = 0; j < board[0].length; j++) {
                        this.boardPane.getChildren().remove(board[k][j].getShape());
                    }
                }
                for (int k = 1; k < 13; k++) {
                    for (int j = i; j > 1; j--) {
                        board[k][j] = board[k][j - 1];
                        board[k][j - 1].getShape().setY(board[k][j - 1].getShape().getY() + 25);
                    }
                    board[k][1] = new Square(k, 1);
                }
                for (int k = 0; k < board.length; k++) {
                    for (int j = 0; j < board[0].length; j++) {
                        this.boardPane.getChildren().add(board[k][j].getShape());
                    }
                }
                i--;
            }
        }
    }
}