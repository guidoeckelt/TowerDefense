package fx.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Vector.Vector2D;
import model.gameobject.tower.Tower;
import model.gameobject.tower.impl.Gunner;

/**
 * Created by Guido on 06.05.2016.
 */
public class MenuViewModel {

    private ObjectProperty<Tower> firstTower = new SimpleObjectProperty<>();

    public MenuViewModel() {
        firstTower.setValue(new Gunner(new Vector2D(0, 0)));
    }

    public Tower getFirstTower() {
        return firstTower.get();
    }

    public ObjectProperty<Tower> firstTowerProperty() {
        return firstTower;
    }
}
