package model.gameobject.minion.impl;

import javafx.scene.paint.Color;
import model.Vector.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.minion.Minion;
import model.util.DrawParamaters;
import model.util.MoveInfo;

import java.util.List;

/**
 * Created by Guido on 11.05.2016.
 */
public class Square extends Minion {

    public Square(Vector2D position) {
        super(position);
        moveDirection = new Vector2D(1,0).normalized();
        speed = 0.5;
        this.color = Color.rgb(1,1,0);
    }

    @Override
    public boolean move(MoveInfo moveInfo) {
        boolean moved;
        if(this.position.getX() >= moveInfo.getGridSize().getWidth()){
            this.position = new Vector2D(0,this.position.getY()+1);
            moved = false;
        }else{
            if(this.canMove(moveInfo.getGameObjects())){
                this.position = this.position.add(moveDirection.normalized().multipliedBy(speed));
                moved = true;
            }else{
                moved = false;
            }
        }
        updateVisual();
        return moved;
    }

    private boolean canMove(List<GameObject> gameObjects){
        for(GameObject gameObject : gameObjects){
            if(this.intersects(gameObject)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void updateVisual() {
        this.visualMeasurePoint = new Vector2D(position.getX() - (size.getWidth() / 2), position.getY() - (size.getHeight() / 2));
    }

    @Override
    public void draw(DrawParamaters drawParamaters) {
        double width = this.size.getWidth() * drawParamaters.getFieldSize();
        double height = this.size.getHeight() * drawParamaters.getFieldSize();
        double x = this.visualMeasurePoint.getX() * drawParamaters.getFieldSize();
        double y = this.visualMeasurePoint.getY() * drawParamaters.getFieldSize();

        drawParamaters.getContext().setFill(color);
        drawParamaters.getContext().fillRect(x, y, width, height);
    }
}
