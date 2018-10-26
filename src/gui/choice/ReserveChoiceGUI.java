package gui.choice;

import Service.GameService;
import enums.YesNo;
import expetions.ReserveException;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.ReserveChoice;
import gui.Fonts;
import gui.ItemScrollPane;
import gui.buttons.InventoryItemButton;
import gui.buttons.YesNoButton;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import model.Item.Asset;
import model.effects.GainAsset;

import java.util.ArrayList;
import java.util.List;

public class ReserveChoiceGUI extends ChoiceDialog {

    public ReserveChoiceGUI(ReserveChoice choice) {
        super(0.6, 0.6, choice);

        List<Asset> assets = choice.get();
        List<Asset> choosen = new ArrayList<>();

        ItemScrollPane scrollPane = new ItemScrollPane();
        for(Asset asset : assets) {
            InventoryItemButton button= new InventoryItemButton(asset,true);
            button. addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    if(choosen.contains(asset)){
                        choosen.remove(asset);
                    }else{
                        choosen.add(asset);
                    }

                    if(choice.isSingleSelect()) {
                        if(choice.getSuccess()==0 ) {
                            GameService.getInstance().getReserve().remove(choosen.get(0));
                            GameService.getInstance().addEffect(new GainAsset(choosen.get(0), GameService.getInstance().getEncounteringInvestigator()));
                            close();
                        }else{
                            try {
                                List<Asset> bought = GameService.getInstance().getReserve().buy(choosen, choice.getSuccess());
                                GameService.getInstance().addEffect(new GainAsset(bought.get(0),GameService.getInstance().getEncounteringInvestigator()));
                                close();
                            }catch (ReserveException ex){
                                GameService.getInstance().addChoice(new InformationChoice("",ex.getMessage(),new ArrayList<>()));
                            }
                        }
                    }else{
                        button.switchSelected();
                    }
                }
            });
            scrollPane.getScrollableChildren().addAll(button);
        }
        scrollPane.disableBackground(true);
        main.getChildren().add(scrollPane);
        if(choice.getSuccess()>0) {
            Label success = new Label("Success: " + choice.getSuccess());
            success.styleProperty().bind(Fonts.getFont(0.4, Fonts.DARK, Fonts.FontTyp.BOLD));
            success.setAlignment(Pos.CENTER);
            success.setTextAlignment(TextAlignment.CENTER);
            getTexts().getChildren().add(success);
        }
        if(!choice.isSingleSelect() ) {

            YesNoButton okButton = new YesNoButton(YesNo.YES);
            okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    try {
                        List<Asset> bought = GameService.getInstance().getReserve().buy(choosen, choice.getSuccess());
                        for (Asset asset: bought){
                            GameService.getInstance().addEffect(new GainAsset(asset,GameService.getInstance().getEncounteringInvestigator()));
                        }
                        close();
                    }catch (ReserveException ex){
                        GameService.getInstance().addChoice(new InformationChoice("",ex.getMessage(),new ArrayList<>()));
                    }
                }
            });
            StackPane.setAlignment(okButton, Pos.BOTTOM_RIGHT);
            main.getChildren().add(okButton);
        }
    }


}
