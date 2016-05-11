package fx.view;

import fx.viewmodel.MainViewModel;
import fx.viewmodel.MenuViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Guido on 05.05.2016.
 */
public class MainView implements Initializable {

    @FXML
    GridPane root;
    @FXML
    StackPane sceneContainer;
    //    @FXML
    Canvas scene;
    @FXML
    AnchorPane menuContainer;

    MenuControl menu;

    private MainViewModel viewModel;

    public MainView(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        menu = new MenuControl(new MenuViewModel());
        menuContainer.getChildren().add(menu.getGraphic());
    }

    public void initCanvas() {
        double width = sceneContainer.getBoundsInParent().getMaxX();
        double height = sceneContainer.getBoundsInParent().getMaxY();
        Dimension2D properSize = viewModel.getProperSize(width, height);
        scene = new Canvas(properSize.getWidth(), properSize.getHeight());
        scene.setOnDragEntered(dragEvent -> {
            if (dragEvent.getDragboard().hasString()) {
                String name = dragEvent.getDragboard().getString();
                viewModel.newToCreatingTower(name, dragEvent.getX(), dragEvent.getY());
            }
        });
        scene.setOnDragOver(dragEvent -> {
            if (dragEvent.getDragboard().hasString()) {
                if (viewModel.isTowerOnFreeSpot()) {
                    dragEvent.acceptTransferModes(TransferMode.ANY);
                    viewModel.updateToCreatingTower(dragEvent.getX(), dragEvent.getY());
                    viewModel.markTowerAsValid();
                } else {
                    viewModel.markTowerAsInvalid();
                }
            }
            dragEvent.consume();
        });
        scene.setOnDragExited(dragEvent -> {
            if (viewModel.getTd().toCreatingTowerProperty().get() != null) {
                viewModel.abortTowerCreation();
            }
        });
        scene.setOnDragDropped(dragEvent -> {
            if (viewModel.isTowerOnFreeSpot()) {
                viewModel.createTower();
                dragEvent.setDropCompleted(true);
            } else {
                viewModel.abortTowerCreation();
                dragEvent.setDropCompleted(false);
            }
        });


        sceneContainer.getChildren().add(scene);
        viewModel.getTd().getView().setScene(scene);
    }

}
