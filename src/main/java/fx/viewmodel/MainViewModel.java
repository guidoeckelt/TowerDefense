package fx.viewmodel;

import javafx.geometry.Dimension2D;
import model.TowerDefense;
import model.gameobject.GameObject;
import model.gameobject.tower.BuildStatus;
import model.gameobject.tower.Tower;
import model.gameobject.tower.impl.Gunner;
import model.vector.Vector2D;

import java.util.LinkedList;

/**
 * Created by Guido on 05.05.2016.
 */
public class MainViewModel {

    private TowerDefense td;
    private LinkedList<Dimension2D> properSizes = new LinkedList<>();

    public MainViewModel(TowerDefense td) {
        this.td = td;
        initSuitableSizes();
    }

    public void createNewPossibleTower(String name, double mouseX, double mouseY) {
        double x = (mouseX / td.getView().getFieldSize());
        double y = (mouseY / td.getView().getFieldSize());
        Vector2D suitablePosition = getSuitablePosition(x, y);
        Tower tower = new Gunner(new Vector2D(suitablePosition.getX(), suitablePosition.getY()));
        td.setToCreatingTower(tower);
    }

    public void updateNewPossibleTower(double mouseX, double mouseY) {
        double x = (mouseX / td.getView().getFieldSize());
        double y = (mouseY / td.getView().getFieldSize());
        Vector2D suitablePosition = getSuitablePosition(x, y);
        td.toCreatingTowerProperty().get().setPosition(suitablePosition);
    }

    public boolean isTowerOnFreeSpot() {
        if(td.getGameObjects().size() > 0){
            for(GameObject gameObject : td.getGameObjects()){
                if(td.toCreatingTowerProperty().get().intersects(gameObject)){
                    return false;
                }
            }
        }
        return true;
    }

    public void markTowerAsInvalid() {
        td.getToCreatingTower().setBuildStatus(BuildStatus.INVALID);
    }

    public void markTowerAsValid() {
        td.getToCreatingTower().setBuildStatus(BuildStatus.VALID);
    }

    public void addNewPossibleTower() {
        td.getToCreatingTower().setBuildStatus(BuildStatus.BUILD);
        td.addGameObject(td.getToCreatingTower());
        clearNewPossibleTower();
    }

    public void clearNewPossibleTower() {
        td.toCreatingTowerProperty().setValue(null);
    }

    private Vector2D getSuitablePosition(double rawX, double rawY) {
        Vector2D suitablePosition;
        double X = Math.round(rawX);
        double Y = Math.round(rawY);
        if (rawX != X || rawY != Y) {
            suitablePosition = new Vector2D(X, Y);
        } else {
            suitablePosition = new Vector2D(rawX, rawY);
        }
        return suitablePosition;
    }

    public Dimension2D getProperSize(double width, double height) {
        //return new Dimension2D(width, height);
        Dimension2D properSize;
        double properWidth = -1;
        double properHeight = -1;
        if (properSizes.contains(new Dimension2D(width, height))) {
            properSize = new Dimension2D(width, height);
        } else {
            for (Dimension2D size : properSizes) {
                if (properWidth < 0) {
                    if (size.getWidth() >= width) {
                        if (size.getWidth() == width) {
                            properWidth = width;
                        } else {
                            properWidth = properSizes.get(properSizes.indexOf(size) - 1).getWidth();
                        }
                    }

                    if (properSizes.getLast() == size) {
                        properWidth = properSizes.getLast().getWidth();
                    }
                }
                if (properHeight < 0) {
                    if (size.getHeight() >= height) {
                        if (size.getHeight() == height) {
                            properHeight = height;
                        } else {
                            properHeight = properSizes.get(properSizes.indexOf(size) - 1).getHeight();
                        }
                    }
                    if (properSizes.getLast() == size) {
                        properHeight = properSizes.getLast().getHeight();
                    }
                }
            }
            System.out.println("CanvasSize = " + properWidth + " : " + properHeight);
            properSize = new Dimension2D(properWidth, properHeight);
        }
        return properSize;
    }

    private void initSuitableSizes() {
        properSizes.add(new Dimension2D(800, 600));
        properSizes.add(new Dimension2D(1024, 768));
        properSizes.add(new Dimension2D(1280, 768));
        properSizes.add(new Dimension2D(1280, 830));
        properSizes.add(new Dimension2D(1280, 960));
        properSizes.add(new Dimension2D(1450, 1050));
        properSizes.add(new Dimension2D(1440, 900));
        properSizes.add(new Dimension2D(1600, 1200));
        properSizes.add(new Dimension2D(1900, 1080));
        properSizes.add(new Dimension2D(1900, 1200));
    }

    public TowerDefense getTd() {
        return td;
    }

}
