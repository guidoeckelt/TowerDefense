package model.gameobject.minion;

import interfaces.Moveable;
import javafx.geometry.Dimension2D;
import model.gameobject.GameObject;
import model.vector.Vector2D;

/**
 * Created by Guido on 11.05.2016.
 */
public abstract class Minion extends GameObject implements Moveable {

    protected Vector2D moveDirection;
    protected double speed;

    public Minion(Vector2D position) {
        super(position, new Dimension2D(1, 1));
    }

}
