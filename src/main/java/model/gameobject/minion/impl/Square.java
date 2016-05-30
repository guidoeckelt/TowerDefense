package model.gameobject.minion.impl;

import javafx.scene.paint.Color;
import model.gameobject.GameObject;
import model.gameobject.minion.Minion;
import model.util.DrawParamaters;
import model.util.MoveInfo;
import model.vector.Vector2D;

import java.util.List;

/**
 * Created by Guido on 11.05.2016.
 */
public class Square extends Minion {

    public Square(Vector2D position) {
        super(position);
        moveDirection = new Vector2D(1,0).normalized();
        speed = 1;
        this.color = Color.rgb(1,1,0);
    }

    @Override
    public boolean move(MoveInfo moveInfo) {
        if(this.position.getX() >= moveInfo.getGridSize().getWidth()){
            this.position = new Vector2D(0,this.position.getY()+1);
            return false;
        }else{
            GameObject collidingGameObject = this.checkForIntersectionWith(moveInfo.getGameObjects());
            if(collidingGameObject == null){
                this.position = this.position.add(moveDirection.multipliedBy(speed));
                updateVisual();
                return true;
            }else{
                //double distance = collidingGameObject.getPosition().
            }
        }
        return false;
    }

    private GameObject checkForIntersectionWith(List<GameObject> gameObjects){
        for(GameObject gameObject : gameObjects){
            if(this != gameObject && this.intersects(gameObject)){
                return gameObject;
            }
        }
        return null;
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
