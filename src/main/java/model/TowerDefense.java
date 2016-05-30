package model;

import interfaces.Moveable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import model.gameobject.GameObject;
import model.gameobject.minion.Minion;
import model.gameobject.minion.impl.Square;
import model.gameobject.tower.Tower;
import model.map.AvailableMaps;
import model.map.Map;
import model.map.impl.FirstMap;
import model.util.MoveInfo;
import model.util.ViewParameters;
import model.vector.Vector2D;

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
    private List<AvailableMaps> availableMaps;
    private Map currentMap;
    private Dimension2D gridSize;
    //GameLoop
    private Timer timer;
    private long delay;
    private TimerTask gameLoop;

    //MenuInfo
    private ObjectProperty<Tower> toCreatingTower = new SimpleObjectProperty<>();


    public TowerDefense() {
        loadAvailableMaps();

        this.view = new View();
        this.delay = 100;
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

        view.render(new ViewParameters(gameObjects, currentMap, toCreatingTower.get()));
    }

    private void moveAllObjects(){
        if(moveableObjects.size() > 0){
            for (int i = 0; i < moveableObjects.size();i++) {
                Moveable moveable = moveableObjects.get(i);
                if(!moveable.move(new MoveInfo(currentMap.getGridSize(), gameObjects))){
                    if(moveable instanceof GameObject){
                        this.removeGameObject(gameObjects.get(gameObjects.indexOf((moveable))));
                    }
                }
            }
        }
    }

    private void createMinion(){
        long lastminioncreation = currentMap.getCurrentLevel().getLastminioncreation();
        long miniontimeout = currentMap.getCurrentLevel().getMiniontimeout();
        if(lastminioncreation == -1||(lastminioncreation + miniontimeout) < new Date().getTime()) {
            double x = 0;
            double y = (currentMap.getGridSize().getHeight()/2);

            Minion minion = new Square(new Vector2D(x, y));
            this.addGameObject(minion);
            currentMap.getCurrentLevel().setLastminioncreation(new Date().getTime());
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

    private void loadMap(AvailableMaps availableMaps){
        switch (availableMaps){
            case FirstMap: currentMap = new FirstMap(); break;
            default: System.out.println(availableMaps);
        }
    }


    public void start() {
        loadMap(AvailableMaps.FirstMap);
        timer.scheduleAtFixedRate(gameLoop, 0,delay);
    }

    private void loadAvailableMaps(){
        availableMaps = new ArrayList<>();
        availableMaps.add(AvailableMaps.FirstMap);
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
