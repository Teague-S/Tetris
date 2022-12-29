import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square {
    private Rectangle shape;
    private Color color;
    private boolean occupied = false;

    public Square(double x, double y) {
        this.shape = new Rectangle(Constants.SQUARE_WIDTH, Constants.SQUARE_WIDTH,
                x < 1 || x > 12 || y < 1 || y > 24 ? Color.GREY : Color.BLACK);
        this.shape.setX(x * Constants.SQUARE_WIDTH);
        this.shape.setY(y * Constants.SQUARE_WIDTH);
        this.shape.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
    }

    public Rectangle getShape() {
        return this.shape;
    }

    public boolean isOccupied() {
        return this.occupied;
    }

    public void setOccupied() {
        this.occupied = true;
    }

    public void setColor(Color newColor) {
        this.color = newColor;
        this.shape.setFill(newColor);
    }
}