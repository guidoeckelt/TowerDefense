package model.gameobject.tower.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Vector.Vector2D;
import model.gameobject.tower.BuildStatus;
import model.gameobject.tower.Tower;

/**
 * Created by Guido on 08.05.2016.
 */
public class Gunner extends Tower {

    public Gunner(Vector2D position) {
        super(position);
        this.name = "Gunner";
        this.cost = 50;
        this.dmg = 10;
        this.attackSpeed = 3;

        this.color = Color.color(0, 1, 0);
    }

    @Override
    public void updateVisual() {
        this.visualMeasurePoint = new Vector2D(position.getX() - (size.getWidth() / 2), position.getY() - (size.getHeight() / 2));
    }

    public void draw(GraphicsContext context, double fieldSize) {
        if (buildStatus == BuildStatus.INVALID) {
            context.setFill(Color.RED);
        } else if (buildStatus == BuildStatus.VALID) {
            context.setFill(Color.DARKGREEN);
        } else if (buildStatus == BuildStatus.BUILD) {
            context.setFill(color);
        }
        double width = this.size.getWidth() * fieldSize;
        double height = this.size.getHeight() * fieldSize;
        double x = this.visualMeasurePoint.getX() * fieldSize;
        double y = this.visualMeasurePoint.getY() * fieldSize;
        context.fillRect(x, y, width, height);
    }

}
