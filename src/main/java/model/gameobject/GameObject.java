package model.gameobject;

import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;
import model.util.DrawParamaters;
import model.vector.Vector2D;

/**
 * Created by Guido on 05.05.2016.
 */
public abstract class GameObject {

    protected Vector2D position;
    protected Dimension2D size;

    protected Vector2D visualMeasurePoint;
    protected Color color;

    public GameObject(Vector2D position, Dimension2D size) {
        this.position = position;
        this.size = size;

        this.color = Color.rgb(0, 0, 1);
        this.updateVisual();
    }

    public boolean intersects(GameObject gameObject){
//        System.out.println(this.position.getX()+" :: "+(gameObject.getPosition().getX()+gameObject.getSize().getWidth()));
//        System.out.println(this.position.getY()+" :: "+(gameObject.getPosition().getY()+gameObject.getSize().getHeight()));
        if(this.position.getX() <= gameObject.getPosition().getX()+gameObject.getSize().getWidth() &&
                this.position.getX() + this.size.getWidth() >= gameObject.getPosition().getX()){
            if(this.position.getY() <= gameObject.getPosition().getY()+gameObject.getSize().getHeight() &&
                    this.position.getY() + this.size.getHeight() >= gameObject.getPosition().getY()){
                return true;
            }
        }
        return false;
    }

    public abstract void draw(DrawParamaters drawParamaters);

    public abstract void updateVisual();

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
        this.updateVisual();
    }

    public Dimension2D getSize() {
        return size;
    }

    public void setSize(Dimension2D size) {
        this.size = size;
    }

    public Vector2D getVisualMeasurePoint() {
        return visualMeasurePoint;
    }

    public Color getColor() {
        return color;
    }

}
