package model.util;

import javafx.geometry.Dimension2D;
import model.gameobject.GameObject;

import java.util.List;

/**
 * Created by Guido on 12.05.2016.
 */
public class MoveInfo {

    private Dimension2D gridSize;
    private List<GameObject> gameObjects;

    public MoveInfo(Dimension2D gridSize, List<GameObject> gameObjects) {
        this.gridSize = gridSize;
        this.gameObjects = gameObjects;
    }

    public Dimension2D getGridSize() {
        return gridSize;
    }

    public void setGridSize(Dimension2D gridSize) {
        this.gridSize = gridSize;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }
}
