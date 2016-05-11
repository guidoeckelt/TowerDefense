package model.gameobject.minion;

import javafx.geometry.Dimension2D;
import model.Vector.Vector2D;
import model.gameobject.GameObject;

/**
 * Created by Guido on 11.05.2016.
 */
public abstract class Minion extends GameObject {

    public Minion(Vector2D position) {
        super(position, new Dimension2D(1, 1));
    }

}
