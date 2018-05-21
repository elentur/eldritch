package gui;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class Effects {

    public static ColorAdjust hover = new ColorAdjust(0.0, 0.1, 0.3, 0.0);
    public static ColorAdjust disabled = new ColorAdjust(0.0, -0.8, 0.4, 0.0);
    public static DropShadow dropShadow = new DropShadow(BlurType.GAUSSIAN,Color.BLACK,10,0,3,3);
}
