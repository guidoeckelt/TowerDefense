package model.gameobject.minion.impl;

import javafx.scene.canvas.GraphicsContext;
import model.Vector.Vector2D;
import model.gameobject.minion.Minion;

/**
 * Created by Guido on 11.05.2016.
 */
public class Square extends Minion {

    public Square(Vector2D position) {
        super(position);
    }

    @Override
    public void draw(GraphicsContext context, double fieldSize) {

    }

    @Override
    public void updateVisual() {

    }
}
