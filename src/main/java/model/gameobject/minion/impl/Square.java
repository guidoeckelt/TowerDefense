package model.gameobject.minion.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Vector.Vector2D;
import model.gameobject.minion.Minion;
import model.gameobject.tower.BuildStatus;
import model.util.MoveEvent;

/**
 * Created by Guido on 11.05.2016.
 */
public class Square extends Minion {

    public Square(Vector2D position) {
        super(position);

        this.color = Color.rgb(1,1,0);
    }

    @Override
    public void move(MoveEvent moveEvent) {
        if(this.position.getX() >= moveEvent.getGridSize().getWidth()){
            this.position = new Vector2D(0,this.position.getY()+1);
        }else{
            this.position = new Vector2D(this.position.getX()+1,this.position.getY());
        }
        updateVisual();
    }

    @Override
    public void updateVisual() {
        this.visualMeasurePoint = new Vector2D(position.getX() - (size.getWidth() / 2), position.getY() - (size.getHeight() / 2));
    }

    @Override
    public void draw(GraphicsContext context, double fieldSize) {
        double width = this.size.getWidth() * fieldSize;
        double height = this.size.getHeight() * fieldSize;
        double x = this.visualMeasurePoint.getX() * fieldSize;
        double y = this.visualMeasurePoint.getY() * fieldSize;

        context.setFill(color);
        context.fillRect(x, y, width, height);
    }
}
