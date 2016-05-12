package model;

import interfaces.Moveable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swt.FXCanvas;
import javafx.geometry.Dimension2D;
import model.Vector.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.minion.Minion;
import model.gameobject.minion.impl.Square;
import model.gameobject.tower.Tower;
import model.util.MoveEvent;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Guido on 11.05.2016.
 */
public class TowerDefense {

    private View view;
    private ObservableList<GameObject> gameObjects = FXCollections.observableArrayList();
    private ObservableList<Moveable> moveableObjects = FXCollections.observableArrayList();

    private ObjectProperty<Tower> toCreatingTower = new SimpleObjectProperty<>();
    private Date lastminioncreation;
    private long miniontimeout;

    private Timer timer;
    private long delay;
    private TimerTask gameLoop;

    public TowerDefense() {
        this.view = new View();
        this.delay = 60;
        this.miniontimeout = 250;
        this.timer = new Timer(true);
        this.gameLoop = new TimerTask() {
            @Override
            public void run() {
                gameLoop();
            }
        };
    }

    private void gameLoop() {
        moveAllObjects();

        if(lastminioncreation == null ||
                (lastminioncreation.getTime() + miniontimeout) < new Date().getTime()){
            createMinion();
            lastminioncreation = new Date();
        }

        view.render(gameObjects, toCreatingTower.get());
    }

    private void moveAllObjects(){
        if(moveableObjects.size() > 0){
            Dimension2D gridSize = new Dimension2D(this.view.getGridWidth(),this.view.getGridHeight());
            for (Moveable moveable : moveableObjects) {
                int index = this.gameObjects.indexOf(moveable);
                List<GameObject> gameObjects = this.gameObjects.subList(index,index+1);
                moveable.move(new MoveEvent(gridSize, gameObjects));
            }
        }
    }

    private void createMinion(){
        double x = 0;
        double y = 0.5;

        Minion minion = new Square(new Vector2D(x,y));
        gameObjects.add(minion);
        moveableObjects.add(minion);
    }


    public void start() {
       timer.scheduleAtFixedRate(gameLoop, 0,delay);
    }

    public View getView() {
        return view;
    }

    public ObservableList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Tower getToCreatingTower() {
        return toCreatingTower.get();
    }

    public void setToCreatingTower(Tower toCreatingTower) {
        this.toCreatingTower.set(toCreatingTower);
    }

    public ObjectProperty<Tower> toCreatingTowerProperty() {
        return toCreatingTower;
    }
}
