package gui.effectoverlays;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.effects.Improve;

import java.nio.IntBuffer;

public class ImproveEffectOverlay extends Overlay {
    private final static Image loreImage = new Image("images/effect/Lore_Improvement.png",200,200,true,true,false);
    private final static Image influenceImage = new Image("images/effect/Influence_Improvement.png",200,200,true,true,false);
    private final static Image observationImage = new Image("images/effect/Observation_Improvement.png",200,200,true,true,false);
    private final static Image strengthImage = new Image("images/effect/Strength_Improvement.png",200,200,true,true,false);
    private final static Image willImage = new Image("images/effect/Will_Improvement.png",200,200,true,true,false);

    public ImproveEffectOverlay(Improve effect) {
        super(effect);
        if(effect.getTestType()==null){
            effect.init();
            return;
        }
        ImagePattern img = null ;
        int startX=97;
        int startY=5;
        if(effect.getValue()==1){
            startX=5;
             startY=97;
        }
        WritablePixelFormat<IntBuffer> format = WritablePixelFormat.getIntArgbInstance();
        int[] buffer = new int[100 * 100];
        switch (effect.getTestType()){
            case LORE:
                loreImage.getPixelReader().getPixels(startX,startY,100,100,format,buffer,0,100);
                break;
            case INFLUENCE:
                influenceImage.getPixelReader().getPixels(startX,startY,100,100,format,buffer,0,100);
                break;
            case OBSERVATION:
                observationImage.getPixelReader().getPixels(startX,startY,100,100,format,buffer,0,100);
                break;
            case STRENGTH:
                strengthImage.getPixelReader().getPixels(startX,startY,100,100,format,buffer,0,100);
                break;
            case WILL:
                willImage.getPixelReader().getPixels(startX,startY,100,100,format,buffer,0,100);
                break;

        }
        WritableImage wimg = new WritableImage(100,100);
        wimg.getPixelWriter().setPixels(0,0,100,100,format,buffer,0,100);
        img=new ImagePattern(wimg);
        Circle circle = new Circle(50);
        circle.setFill(img);

        setCord(effect.getInvestigator());
        this.getChildren().addAll(circle);
        this.setMouseTransparent(true);
    }
}
