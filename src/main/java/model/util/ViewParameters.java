package model.util;

import javafx.geometry.Dimension2D;
import model.WayPoint;
import model.gameobject.GameObject;
import model.gameobject.tower.Tower;

import java.util.List;

/**
 * Created by Guido on 14.05.2016.
 */
public class ViewParameters {
    private final List<GameObject> toDrawingGameObjects;
    private final List<WayPoint> wayPoints;
    private final Tower toCreatingTower;
    private final Dimension2D gridSize;

    public ViewParameters(List<GameObject> toDrawingGameObjects, List<WayPoint> wayPoints, Tower toCreatingTower, Dimension2D gridSize) {
        this.toDrawingGameObjects = toDrawingGameObjects;
        this.wayPoints = wayPoints;
        this.toCreatingTower = toCreatingTower;
        this.gridSize = gridSize;
    }

    public List<GameObject> getToDrawingGameObjects() {
        return toDrawingGameObjects;
    }

    public List<WayPoint> getWayPoints() {
        return wayPoints;
    }

    public Tower getToCreatingTower() {
        return toCreatingTower;
    }

    public Dimension2D getGridSize() {
        return gridSize;
    }
}
