package fx.view;

import fx.viewmodel.MenuViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import model.gameobject.tower.Tower;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Guido on 06.05.2016.
 */
public class MenuView implements Initializable {

    private final MenuViewModel viewModel;
    @FXML
    GridPane root;
    @FXML
    HBox container;
    @FXML
    ImageView towerGraphic;
    @FXML
    Label lblName;
    @FXML
    Label lblCost;
    @FXML
    Label lblDamage;
    @FXML
    Label lblAttackSpeed;

    public MenuView(MenuViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        setTower(viewModel.getFirstTower());
        container.setOnDragDetected(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown()) {
                Dragboard dragboard = container.startDragAndDrop(TransferMode.ANY);

                ClipboardContent content = new ClipboardContent();
                content.putString(viewModel.getFirstTower().getName());
                dragboard.setContent(content);
                mouseEvent.consume();
            }
        });
    }

    private void setTower(Tower tower) {
        Rectangle graphic = new Rectangle();
        graphic.setWidth(tower.getSize().getWidth());
        graphic.setHeight(tower.getSize().getHeight());
        graphic.setFill(tower.getColor());
        towerGraphic.setImage(graphic.snapshot(null, null));
        lblName.setText(String.valueOf(tower.getName()));
        lblCost.setText(String.valueOf(tower.getCost()));
        lblDamage.setText(String.valueOf(tower.getDmg()));
        lblAttackSpeed.setText(String.valueOf(tower.getAttackSpeed()));
    }

}
