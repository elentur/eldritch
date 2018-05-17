package gui;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Dialog extends Stage {

    public Dialog(String s) {
        super();
        VBox root = new VBox();
        Scene scene = new Scene(root, 400, 150);
        scene.getStylesheets().add("css/rootStyle.css");
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        this.setAlwaysOnTop(true);
        this.setX(0);

    }
}
