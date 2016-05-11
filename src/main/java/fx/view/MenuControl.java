package fx.view;

import fx.viewmodel.MenuViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

/**
 * Created by Guido on 06.05.2016.
 */
public class MenuControl {

    private MenuViewModel viewModel;
    private MenuView view;
    private Node graphic;

    public MenuControl(MenuViewModel viewModel) {
        this.viewModel = viewModel;
        this.view = new MenuView(viewModel);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Menu.fxml"));
        loader.setController(view);
        try {
            this.graphic = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node getGraphic() {
        return graphic;
    }
}
