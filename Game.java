import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Game {
    private Board board;
    private Pane pane;
    private Piece currentPiece;
    private boolean playing = true;
    private Timeline timeline;
    private int[] currentPieceCoords = { 0, 0 };
    private Label label;

    public Game(Pane pane, Label label) {
        this.label = label;
        this.pane = pane;
        this.board = new Board(this.pane);
        this.currentPiece = new Piece(this.pane);
        this.setupTimeline();
        pane.setOnKeyPressed(KeyEvent -> handleKeyPress(KeyEvent));
    }

    public void moveDown() {
        Square[][] currentSquares = this.board.getBoard();
        int[][] currentCoords = this.currentPiece.getCoords();
        boolean downOpen = true;
        for (int[] coord : currentCoords) {
            if (coord[0] == 23 || currentSquares[coord[1] + 6][coord[0] + 2].isOccupied()) {
                downOpen = false;
            }
        }
        if (downOpen) {
            this.currentPiece.moveDown();
            this.currentPieceCoords[1]++;
        } else {
            for (int[] coord : currentCoords) {
                currentSquares[coord[1] + 6][coord[0] + 1].setOccupied();
                currentSquares[coord[1] + 6][coord[0] + 1].setColor(this.currentPiece.getColor());
            }
            if (board.gameOver()) {
                this.timeline.stop();
                this.playing = false;
                this.label.setText("You lose!");
                this.reset();
                return;
            }
            this.board.clearRows();
            this.reset();
            this.currentPiece.removeFromPane();
            this.currentPiece = new Piece(this.pane);
        }
    }

    public void moveRight() {
        Square[][] currentSquares = this.board.getBoard();
        int[][] currentCoords = this.currentPiece.getCoords();
        boolean rightOpen = true;
        for (int[] coord : currentCoords) {
            if (coord[1] + 6 >= 12 || currentSquares[coord[1] + 6 + 1][coord[0] + 1].isOccupied()) {
                rightOpen = false;
            }
        }
        if (rightOpen) {
            this.currentPiece.moveRight();
            this.currentPieceCoords[0]++;
        }
    }

    public void moveLeft() {
        Square[][] currentSquares = this.board.getBoard();
        int[][] currentCoords = this.currentPiece.getCoords();
        boolean leftOpen = true;
        for (int[] coord : currentCoords) {
            if (coord[1] + 6 <= 1 || currentSquares[coord[1] + 6 - 1][coord[0] + 1].isOccupied()) {
                leftOpen = false;
            }
        }
        if (leftOpen) {
            this.currentPiece.moveLeft();
            this.currentPieceCoords[0]--;
        }
    }

    public void rotate() {
        int[][] startingCoords = this.currentPiece.getStartingCoords();
        Square[][] currentSquares = this.board.getBoard();
        boolean canRotate = true;
        for (int[] coord : startingCoords) {
            int temp1 = coord[0];
            int temp0 = -1 * coord[1];
            if (temp0 + this.currentPieceCoords[1] + 1 < 1 || temp0 + this.currentPieceCoords[1] + 1 > 23
                    || temp1 + this.currentPieceCoords[0] + 6 <= 1 || temp1 + this.currentPieceCoords[0] + 6 > 12
                    || currentSquares[temp1 + this.currentPieceCoords[0] + 6][temp0 + this.currentPieceCoords[1] + 1]
                            .isOccupied()) {
                canRotate = false;
                break;
            }
        }
        if (canRotate) {
            this.currentPiece.rotate(this.currentPieceCoords[0], this.currentPieceCoords[1]);
        }
    }

    public void fullDown() {
        Piece piece = this.currentPiece;
        while (this.currentPiece.equals(piece)) {
            this.moveDown();
        }
    }

    private void setupTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(.5),
                (ActionEvent e) -> this.moveDown());
        this.timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void handleKeyPress(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        if (keyPressed == KeyCode.UP && this.playing) {
            this.rotate();
        } else if (keyPressed == KeyCode.DOWN && this.playing) {
            this.moveDown();
        } else if (keyPressed == KeyCode.RIGHT && this.playing) {
            this.moveRight();
        } else if (keyPressed == KeyCode.LEFT && this.playing) {
            this.moveLeft();
        } else if (keyPressed == KeyCode.SPACE && this.playing) {
            this.fullDown();
        } else if (keyPressed == KeyCode.P) {
            if (this.playing) {
                this.timeline.pause();
                this.label.setText("Game Paused");
            } else {
                this.timeline.play();
                this.label.setText("Tetris");
            }
            this.playing = !this.playing;
        }
        e.consume();
    }

    private void reset() {
        this.currentPieceCoords = new int[] { 0, 0 };
    }
}