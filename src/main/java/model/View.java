package model;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.gameobject.GameObject;
import model.gameobject.tower.Tower;

/**
 * Created by Guido on 05.05.2016.
 */
public class View {

    private double gridWidth;
    private double gridHeight;
    private double fieldSize;
    private Canvas scene;
    private GraphicsContext context;
    private DoubleProperty width = new SimpleDoubleProperty();
    private DoubleProperty height = new SimpleDoubleProperty();


    public View() {
        fieldSize = 10;
    }

    public void setScene(Canvas scene) {
        this.scene = scene;
        this.context = scene.getGraphicsContext2D();
        width.addListener((observable, oldValue, newValue) -> {
            gridWidth = newValue.doubleValue() / fieldSize;
        });
        height.addListener((observable, oldValue, newValue) -> {
            gridHeight = newValue.doubleValue() / fieldSize;
        });
        width.bind(scene.widthProperty());
        height.bind(scene.heightProperty());

    }

    public void render(ObservableList<GameObject> gameObjectList, Tower tower) {
        Platform.runLater(() -> {
            context.clearRect(0, 0, width.doubleValue(), height.doubleValue());
            drawBackground();
            drawGameObjects(gameObjectList);
            if (tower != null) {
                drawToCreatingTower(tower);
            }

        });
    }

    private void drawGameObjects(ObservableList<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(context, fieldSize);
        }
    }

    private void drawToCreatingTower(Tower tower) {
        tower.draw(context, fieldSize);
    }

    private void drawBackground() {
        this.context.setStroke(Color.BLACK);
        this.context.setFill(Color.GRAY);
        for (double y = 0; y < this.gridHeight; y++) {
            for (double x = 0; x < this.gridWidth; x++) {
                this.context.fillRect(x * fieldSize, y * fieldSize, fieldSize, fieldSize);
                //this.context.strokeRect(x*fieldSize,y*fieldSize,fieldSize,fieldSize);
            }
        }
    }

    public double getWidth() {
        return width.get();
    }

    public DoubleProperty widthProperty() {
        return width;
    }

    public double getHeight() {
        return height.get();
    }

    public DoubleProperty heightProperty() {
        return height;
    }

    public double getFieldSize() {
        return fieldSize;
    }

    public double getGridWidth() {
        return gridWidth;
    }

    public double getGridHeight() {
        return gridHeight;
    }
}
