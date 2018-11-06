package gui.choice;

import Service.GameService;
import enums.TestType;
import enums.YesNo;
import expetions.ItemChoiceException;
import expetions.SkillChoiceException;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.SkillChoice;
import gui.Fonts;
import gui.ItemScrollPane;
import gui.buttons.Button;
import gui.buttons.YesNoButton;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class SkillChoiceGUI extends ChoiceDialog {

    public SkillChoiceGUI(SkillChoice choice) {
        super(0.6, 0.6, choice);

        List<TestType> testTypes;
        try {
            testTypes = choice.get();
        } catch (SkillChoiceException ex) {
            GameService.getInstance().addChoice(new InformationChoice("", ex.getMessage(), new ArrayList<>()));
            close();
            return;
        }
        List<TestType> chosen = new ArrayList<>();

        ItemScrollPane scrollPane = new ItemScrollPane();
        for (TestType type : testTypes) {
            Button button = new Button(new Image("images/skill/" + type.getKey().replace("${", "&").replace("}", "") + ".png", 100, 100, true, true, true));
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {

                    if (chosen.contains(type)) {
                        chosen.remove(type);
                    } else {
                        chosen.add(type);
                    }

                    button.switchSelected();

                }
            });
            scrollPane.getScrollableChildren().addAll(button);
        }
        scrollPane.disableBackground(true);
        main.getChildren().add(scrollPane);

        Label success = new Label("Improvements: " + choice.getNumber());
        success.styleProperty().bind(Fonts.getFont(0.4, Fonts.DARK, Fonts.FontTyp.BOLD));
        success.setAlignment(Pos.CENTER);
        success.setTextAlignment(TextAlignment.CENTER);
        getTexts().getChildren().add(success);


        YesNoButton okButton = new YesNoButton(YesNo.YES);
        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                try {
                    choice.getChosen().clear();
                    choice.getChosen().addAll(chosen);
                    choice.acceptChoice();
                    close();
                } catch (SkillChoiceException ex) {
                    GameService.getInstance().addChoice(new InformationChoice("", ex.getMessage(), new ArrayList<>()));
                }
            }
        });
        StackPane.setAlignment(okButton, Pos.BOTTOM_RIGHT);
        main.getChildren().add(okButton);
    }


}
