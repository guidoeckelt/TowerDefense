package model;

import interfaces.Moveable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import model.Vector.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.minion.Minion;
import model.gameobject.minion.impl.Square;
import model.gameobject.tower.Tower;
import model.util.MoveInfo;
import model.util.ViewParameters;

import java.util.*;

/**
 * Created by Guido on 11.05.2016.
 */
public class TowerDefense {

    private View view;
    //Objects
    private ObservableList<GameObject> gameObjects = FXCollections.observableArrayList();
    private ObservableList<Moveable> moveableObjects = FXCollections.observableArrayList();
    //MapInfo
    private Dimension2D gridSize;
    private LinkedList<WayPoint> wayPoints = new LinkedList<>();
        //LevelInfo
    private Date lastminioncreation;
    private long miniontimeout;
    //MenuInfo
    private ObjectProperty<Tower> toCreatingTower = new SimpleObjectProperty<>();

    private Timer timer;
    private long delay;
    private TimerTask gameLoop;

    public TowerDefense() {
        this.view = new View();
        this.delay = 60;
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
        createMinion();
        view.render(new ViewParameters(gameObjects, wayPoints, toCreatingTower.get(),gridSize));
    }

    private void moveAllObjects(){
        if(moveableObjects.size() > 0){
            for (int i = 0; i < moveableObjects.size();i++) {
                Moveable moveable = moveableObjects.get(i);
                int index = this.gameObjects.indexOf(moveable);
                List<GameObject> gameObjects = this.gameObjects.subList(index,index+1);
                if(!moveable.move(new MoveInfo(gridSize, gameObjects))){
                    this.removeGameObject(gameObjects.get(gameObjects.indexOf(((GameObject) moveable))));
                }
            }
        }
    }

    private void createMinion(){
        if(lastminioncreation == null||(lastminioncreation.getTime() + miniontimeout) < new Date().getTime()) {
            double x = 0;
            double y = (gridSize.getHeight()/2);

            Minion minion = new Square(new Vector2D(x, y));
            this.addGameObject(minion);
            lastminioncreation = new Date();
        }
    }

    public void addGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
        if(gameObject instanceof Moveable){
            moveableObjects.add((Moveable)gameObject);
        }
    }

    private void removeGameObject(GameObject gameObject){
        gameObjects.remove(gameObject);
        if(gameObject instanceof Moveable){
            moveableObjects.remove(gameObject);
        }
    }

    private void loadLevel(){
        //gridSize = new Dimension2D(190,80);
        Vector2D position = new Vector2D(this.gridSize.getWidth()-2,this.gridSize.getHeight()/2);
        WayPoint wayPoint = new WayPoint(position);
        wayPoints.addLast(wayPoint);
        position = new Vector2D(2,this.gridSize.getHeight()/2);
        WayPoint wayPoint2 = new WayPoint(position, wayPoint);
        wayPoints.addFirst(wayPoint2);

        this.miniontimeout = 5000;
    }

    public void start() {
        double width = view.getWidth()/view.getFieldSize();
        double height = view.getHeight()/view.getFieldSize();
        gridSize = new Dimension2D(width, height);
        loadLevel();
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
