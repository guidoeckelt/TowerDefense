package model.gameobject;

import javafx.geometry.Dimension2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Vector.Vector2D;

/**
 * Created by Guido on 05.05.2016.
 */
public abstract class GameObject {

    protected Vector2D position;
    protected Dimension2D size;

    protected Vector2D visualMeasurePoint;
    protected Color color;

    public GameObject(Vector2D position, Dimension2D size) {
        this.position = position;
        this.size = size;

        this.color = Color.rgb(0, 0, 1);
        this.updateVisual();
    }

    public void draw(GraphicsContext context, double fieldSize) {
        context.setFill(color);

        double width = this.size.getWidth() * fieldSize;
        double height = this.size.getHeight() * fieldSize;
        double x = this.visualMeasurePoint.getX() * fieldSize;
        double y = this.visualMeasurePoint.getY() * fieldSize;
        context.fillRect(x, y, width, height);
    }

    private void updateVisual() {
        this.visualMeasurePoint = new Vector2D(position.getX() - (size.getWidth() / 2), position.getY() - (size.getHeight() / 2));
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
        this.updateVisual();
    }

    public Dimension2D getSize() {
        return size;
    }

    public void setSize(Dimension2D size) {
        this.size = size;
    }

    public Vector2D getVisualMeasurePoint() {
        return visualMeasurePoint;
    }

    public Color getColor() {
        return color;
    }
}
