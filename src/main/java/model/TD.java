package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.gameobject.GameObject;
import model.gameobject.tower.Tower;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Guido on 11.05.2016.
 */
public class TD {

    private View view;
    private ObservableList<GameObject> structuresList = FXCollections.observableArrayList();
    private ObjectProperty<Tower> toCreatingTower = new SimpleObjectProperty<>();

    private Timer timer;
    private TimerTask gameLoop;

    public TD() {
        this.view = new View();
        this.timer = new Timer(true);
        this.gameLoop = new TimerTask() {
            @Override
            public void run() {
                gameLoop();
            }
        };
    }

    private void gameLoop() {
        view.render(structuresList, toCreatingTower.get());
    }

    public void start() {
        timer.scheduleAtFixedRate(gameLoop, 0, 60);
    }

    public View getView() {
        return view;
    }

    public ObservableList<GameObject> getStructuresList() {
        return structuresList;
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
