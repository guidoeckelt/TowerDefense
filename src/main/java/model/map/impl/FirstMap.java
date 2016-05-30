package model.map.impl;

import javafx.geometry.Dimension2D;
import model.WayPoint;
import model.level.Level;
import model.level.impl.FirstLevel;
import model.map.Map;
import model.vector.Vector2D;

/**
 * Created by Guido on 22.05.2016.
 */
public class FirstMap extends Map {

    public FirstMap() {
        gridSize = new Dimension2D(190,80);
        //WayPoints
        Vector2D position = new Vector2D(this.gridSize.getWidth()-2,this.gridSize.getHeight()/2);
        WayPoint wayPoint = new WayPoint(position);
        wayPoints.addLast(wayPoint);
        position = new Vector2D(2,this.gridSize.getHeight()/2);
        WayPoint wayPoint2 = new WayPoint(position, wayPoint);
        wayPoints.addFirst(wayPoint2);
    }

    protected void loadLevels() {
        Level level = new FirstLevel();
        levels.add(level);
    }
}
