package model;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.gameobject.GameObject;
import model.gameobject.tower.BuildStatus;
import model.gameobject.tower.Tower;
import model.util.DrawParamaters;
import model.util.ViewParameters;

import java.util.List;

/**
 * Created by Guido on 05.05.2016.
 */
public class View {

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
        width.bind(scene.widthProperty());
        height.bind(scene.heightProperty());

    }
    public void render(ViewParameters viewParameters){
        Platform.runLater(() -> {
            context.clearRect(0, 0, width.doubleValue(), height.doubleValue());
            drawBackground(viewParameters.getGridSize());
            drawWayPoints(viewParameters.getWayPoints());
            drawGameObjects(viewParameters.getToDrawingGameObjects());
            if (viewParameters.getToCreatingTower() != null) {
                drawToCreatingTower(viewParameters.getToCreatingTower());
            }

        });
    }

    private void drawGameObjects(List<GameObject> gameObjects) {
        DrawParamaters drawParamaters = new DrawParamaters(context, fieldSize);
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(drawParamaters);
        }
    }

    private void drawToCreatingTower(Tower tower) {
        double centerX = tower.getPosition().getX()*fieldSize;
        double centerY = tower.getPosition().getY()*fieldSize;
        double radius = tower.getRange()*fieldSize;
        context.setStroke(Color.YELLOW);
        context.strokeOval(centerX-radius,centerY-radius,radius*2,radius*2);

        if (tower.getBuildStatus() == BuildStatus.INVALID) {
            context.setFill(Color.RED);
        } else if (tower.getBuildStatus() == BuildStatus.VALID) {
            context.setFill(Color.DARKGREEN);
        }
        double width = tower.getSize().getWidth() * fieldSize;
        double height = tower.getSize().getHeight() * fieldSize;
        double x = tower.getVisualMeasurePoint().getX() * fieldSize;
        double y = tower.getVisualMeasurePoint().getY() * fieldSize;
        context.fillRect(x, y, width, height);
    }

    private void drawWayPoints(List<WayPoint> wayPoints){
        for(WayPoint wayPoint : wayPoints){
//            double width = fieldSize;
//            double height = fieldSize;
//            double x = (wayPoint.getPosition().getX() * fieldSize)-width/2;
//            double y = (wayPoint.getPosition().getY() * fieldSize)-height/2;
//            context.setFill(Color.rgb(255,90,0));
//            context.fillRect(x, y, width, height);


            double centerX = wayPoint.getPosition().getX()*fieldSize;
            double centerY = wayPoint.getPosition().getY()*fieldSize;
            double radius = 5;
            context.setFill(Color.YELLOW);
            context.fillOval(centerX-radius,centerY-radius,radius*2,radius*2);
        }

    }

    private void drawBackground(Dimension2D gridSize) {
        this.context.setStroke(Color.BLACK);
        this.context.setFill(Color.GRAY);
        for (double y = 0; y < gridSize.getHeight(); y++) {
            for (double x = 0; x < gridSize.getWidth(); x++) {
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
}
