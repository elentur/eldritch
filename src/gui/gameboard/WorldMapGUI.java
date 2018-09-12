package gui.gameboard;

import Service.GameService;
import enums.FieldConnections;
import enums.PathType;
import gui.Fonts;
import gui.buttons.FieldButton;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import model.Field;
import model.GameBoard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WorldMapGUI extends MapGUI {
    private final static Image backgroundImage = new Image("images/gameBoard/GameBoard.jpg");
    private final Rectangle background;
    private final List<FieldButton> fieldButtonList;
    private final Map<FieldConnections, SVGPath> paths;

    public Rectangle getBack() {
        return background;
    }

    public WorldMapGUI(GameBoard gameBoard) {


        fieldButtonList = new ArrayList<>();
        paths = new HashMap<>();

        background = new Rectangle(5804, 3594);
        background.setFill(new ImagePattern(backgroundImage));
        this.getChildren().add(background);

        try {
            File file = new File("./resources/svg/PathsWorldMap.svg");
            Scanner scanner = new Scanner(file);

            for (FieldConnections connections : FieldConnections.values()) {
                if (scanner.hasNext()) {

                    SVGPath path = new SVGPath();
                    path.setContent(scanner.nextLine());
                    path.setFill(Color.TRANSPARENT);
                    path.setVisible(false);
                    Bloom bloom = new Bloom(0.5);
                    path.setEffect(bloom);
                    path.setStrokeWidth(20);
                    path.setStrokeLineCap(StrokeLineCap.ROUND);
                    paths.put(connections, path);

                }
            }
        } catch (FileNotFoundException e) {

        }

        this.getChildren().addAll(paths.values());
        for (Field field : gameBoard.getFields()) {
            switch (field.getFieldID()) {
                case FIELD_1:
                    addFieldButton(field, 270, 530);
                    break;
                case FIELD_2:
                    addFieldButton(field, 115, 1200);
                    break;
                case FIELD_3:
                    addFieldButton(field, 550, 2660);
                    break;
                case FIELD_4:
                    addFieldButton(field, 848, 522);
                    break;
                case FIELD_5:
                    addFieldButton(field, 970, 822);
                    break;
                case FIELD_6:
                    addFieldButton(field, 810, 1170);
                    break;
                case FIELD_7:
                    addFieldButton(field, 1090, 1620);
                    break;
                case FIELD_8:
                    addFieldButton(field, 1460, 1280);
                    break;
                case FIELD_9:
                    addFieldButton(field, 1920, 270);
                    break;
                case FIELD_10:
                    addFieldButton(field, 2380, 1740);
                    break;
                case FIELD_11:
                    addFieldButton(field, 2480, 2670);
                    break;
                case FIELD_12:
                    addFieldButton(field, 2200, 3060);
                    break;
                case FIELD_13:
                    addFieldButton(field, 3310, 150);
                    break;
                case FIELD_14:
                    addFieldButton(field, 3120, 630);
                    break;
                case FIELD_15:
                    addFieldButton(field, 3050, 2730);
                    break;
                case FIELD_16:
                    addFieldButton(field, 3850, 670);
                    break;
                case FIELD_17:
                    addFieldButton(field, 4040, 1680);
                    break;
                case FIELD_18:
                    addFieldButton(field, 3940, 2880);
                    break;
                case FIELD_19:
                    addFieldButton(field, 5280, 680);
                    break;
                case FIELD_20:
                    addFieldButton(field, 4860, 2130);
                    break;
                case FIELD_21:
                    addFieldButton(field, 5060, 2460);
                    break;
                case ARKHAM:
                    addFieldButton(field, 1290, 1050);
                    break;
                case SAN_FRANCISCO:
                    addFieldButton(field, 300, 1000);
                    break;
                case BUENOS_AIRES:
                    addFieldButton(field, 1270, 2670);
                    break;
                case LONDON:
                    addFieldButton(field, 2280, 822);
                    break;
                case ROME:
                    addFieldButton(field, 2630, 1290);
                    break;
                case ISTANBUL:
                    addFieldButton(field, 3240, 1110);
                    break;
                case TOKYO:
                    addFieldButton(field, 5140, 1350);
                    break;
                case SHANGHAI:
                    addFieldButton(field, 4650, 1700);
                    break;
                case SYDNEY:
                    addFieldButton(field, 5020, 2980);
                    break;
                case AMAZON:
                    addFieldButton(field, 1320, 2070);
                    break;
                case PYRAMIDS:
                    addFieldButton(field, 3050, 1750);
                    break;
                case HEART_OF_AFRICA:
                    addFieldButton(field, 2955, 2335);
                    break;
                case ANTARCTICA:
                    addFieldButton(field, 3165, 3355);
                    break;
                case TUNGUSKA:
                    addFieldButton(field, 4120, 825);
                    break;
                case HIMALAYA:
                    addFieldButton(field, 4000, 1415);
                    break;
                default:
                    break;
            }

        }


    }

    private void addFieldButton(Field field, double x, double y) {
        switch (field.getSpaceType()) {
            case CITY:
                this.getChildren().add(new CityLabel(field.getFieldID().getKey(), x, y));
                break;
            case EXPEDITION:
                this.getChildren().add(new ExpeditionLabel(field.getFieldID().getKey(), x, y));
                break;
            default:
                this.getChildren().add(new FieldLabel(field.getFieldID().getKey(), x, y));
                break;
        }
        FieldButton fieldButton = new FieldButton(field, x, y);
        fieldButtonList.add(fieldButton);
        this.getChildren().add(fieldButton);
    }

    @Override
    public FieldButton getFieldButton(Field field) {
        for (FieldButton fieldButton : fieldButtonList) {
            if (fieldButton.getField().equals(field)) {
                return fieldButton;
            }
        }
        return null;
    }

    @Override
    public void clearPath() {
        for (SVGPath path : paths.values()) {
            path.setVisible(false);
        }
    }

    @Override
    public void showPath(List<Field> path) {
        for (int i = 0; i < path.size() - 1; i++) {
            FieldConnections connection = FieldConnections.getConnection(path.get(i), path.get(i + 1));
            SVGPath svgPath = paths.get(connection);
            if (i == 0) {
                svgPath.setStroke(Fonts.GREEN);
            } else {
                if ((connection.getPathType().equals(PathType.SHIP) && GameService.getInstance().getEncounteringInvestigator().getShipTicket() > 0) ||
                        (connection.getPathType().equals(PathType.TRAIN) && GameService.getInstance().getEncounteringInvestigator().getTrainTicket() > 0)) {
                    svgPath.setStroke(Fonts.BLUE);
                }else{
                    svgPath.setStroke(Fonts.RED);
                }
            }

            svgPath.setVisible(true);
        }
    }

    @Override
    public Map<SVGPath,Boolean> getSvgPaths(List<Field> path) {
        Map<SVGPath,Boolean> svgPaths = new LinkedHashMap<>();
        for (int i = 0; i < path.size() - 1; i++) {
            FieldConnections connection = FieldConnections.getConnection(path.get(i), path.get(i + 1));
            SVGPath svgPath = paths.get(connection);
            svgPaths.put(svgPath,connection.getId1().equals(path.get(i).getFieldID()));
        }
        return svgPaths;
    }
}
