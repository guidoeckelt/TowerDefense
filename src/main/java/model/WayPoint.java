package model;

import model.vector.Vector2D;

import java.util.List;

/**
 * Created by Guido on 14.05.2016.
 */
public class WayPoint {

    private final Vector2D position;
    private final List<Vector2D> routeToNextWayPoint;
    private final WayPoint nextWayPoint;

    public WayPoint(Vector2D position) {
        this(position, null);
    }
    public WayPoint(Vector2D position, WayPoint nextWayPoint) {
        this.position = position;
        this.nextWayPoint = nextWayPoint;
        this.routeToNextWayPoint = calculateShortestPathToNextWayPoint(nextWayPoint);
    }

    private List<Vector2D> calculateShortestPathToNextWayPoint(WayPoint wayPoint){
        return  null;
    }

    public Vector2D getPosition() {
        return position;
    }

    public List<Vector2D> getRouteToNextWayPoint() {
        return routeToNextWayPoint;
    }

    public WayPoint getNextWayPoint() {
        return nextWayPoint;
    }

}
