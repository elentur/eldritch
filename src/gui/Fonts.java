package gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

public class Fonts {
    //public static Font font = Font.loadFont("file:src/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /50);
    public enum FontTyp {
        NORMAL, BOLD, ITALIC
    }
    public static Color DARK = Color.DARKSLATEGREY;
    public static Color WHITE = Color.WHITE;
    public static Color RED = Color.rgb(205, 40, 43);
    public static Color BLUE = Color.rgb(88, 120, 200);
    public static Color GREEN = Color.rgb(0, 170, 80);

    static {
        Font.loadFont("file:resources/fonts/UGLYQUAITALIC.TTF", Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 200);
     Font f=   Font.loadFont("file:resources/fonts/UGLYQUABOLD.TTF", Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 200);
        Font.loadFont("file:resources/fonts/UGLYQUA.TTF", Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 200);
    }

    private static Stage primaryStage;

    public static void init(Stage stage) {
        primaryStage = stage;
    }

    public static StringProperty getFont(double size, Color color, FontTyp type) {
        SimpleStringProperty font = new SimpleStringProperty();

        SimpleDoubleProperty sizeprop = new SimpleDoubleProperty();
        sizeprop.bind(primaryStage.widthProperty().multiply(size * 0.06));
        if (type.equals(FontTyp.ITALIC)) {

            font.bind(Bindings.concat("-fx-font:", sizeprop.intValue(), " 'UglyQua-Italic' ; -fx-text-fill: " + color.toString().replace("0x", "#") + ";"));

        } else if (type.equals(FontTyp.BOLD)) {
            font.bind(Bindings.concat("-fx-font:", sizeprop.intValue(), " 'UglyQua-Bold' ; -fx-text-fill: " + color.toString().replace("0x", "#") + "; "));
        } else {
            font.bind(Bindings.concat("-fx-font:", sizeprop.intValue(), " 'UglyQua' ; -fx-text-fill: " + color.toString().replace("0x", "#") + "; "));
        }
        return font;

    }


}
