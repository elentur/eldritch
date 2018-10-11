package gui.interfaceelements;

import Service.GameService;
import gui.buttons.Button;
import gui.buttons.FieldButton;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.FieldActions;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

public class ContextWheel extends Group {
    private final static Image skipAction = new Image("images/interface/SkipAction.png", 100, 100, true, true);
    private final static Image gainFocus = new Image("images/interface/GainFocus.png", 100, 100, true, true);
    private final static Image buyShipTicket = new Image("images/interface/BuyShipTicket.png", 100, 100, true, true);
    private final static Image buyTrainTicket = new Image("images/interface/BuyTrainTicket.png", 100, 100, true, true);
    private final static Image acquireAsset = new Image("images/interface/AcquireAsset.png", 100, 100, true, true);
    private final static Image trade = new Image("images/interface/Trade.png", 100, 100, true, true);
    private final static Image restAction = new Image("images/interface/restAction.png", 100, 100, true, true);
    private final FieldActions field;
    private boolean isRemoved;


    public ContextWheel(FieldActions field) {
    this.field = field;
        isRemoved=false;
        buildWheel();
        this.setTranslateX(10);
        this.setTranslateY(10);
    }

    private void buildWheel() {
        List<Button> buttons = new ArrayList<>();

        Button skipActionBtn = new Button(skipAction);
        skipActionBtn.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                remove();
                GameService.getInstance().setActiveInvestigator();
            }
        });
        skipActionBtn.setTooltipText(ResourceUtil.get("${skip_action}","ui"));
        buttons.add(skipActionBtn);

        if (field.isHasFocus()) {
            Button focusBtn = new Button(gainFocus);
            focusBtn.setOnMouseClicked(e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    remove();
                    field.gainFocus();
                }
            });
            focusBtn.setTooltipText(ResourceUtil.get("${gain_focus}","ui"));
            buttons.add(focusBtn);
        }
        if (field.isHasRestAction()) {
            Button restBtn = new Button(restAction);
            restBtn.setOnMouseClicked(e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    remove();
                    field.rest();
                }
            });
            restBtn.setTooltipText(ResourceUtil.get("${rest}","ui"));
            buttons.add(restBtn);
        }
        if (field.isHasAcquireAsset()) {
            Button acquireAssetBtn = new Button(acquireAsset);
            acquireAssetBtn.setOnMouseClicked(e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    remove();
                    field.acquireAsset();
                }
            });
            acquireAssetBtn.setTooltipText(ResourceUtil.get("${acquire_asset}","ui"));
            buttons.add(acquireAssetBtn);
        }
        if (field.isHasShipTicket()) {
            Button shipTicketBtn = new Button(buyShipTicket);
            shipTicketBtn.setOnMouseClicked(e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    remove();
                    field.buyShipTicket();
                }
            });
            shipTicketBtn.setTooltipText(ResourceUtil.get("${buy_ship_ticket}","ui"));
            buttons.add(shipTicketBtn);
        }
        if (field.isHasTrainTicket()) {
            Button trainTicketBtn = new Button(buyTrainTicket);
            trainTicketBtn.setOnMouseClicked(e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    remove();
                    field.buyTrainTicket();
                }
            });
             trainTicketBtn.setTooltipText(ResourceUtil.get("${buy_train_ticket}","ui"));
            buttons.add(trainTicketBtn);
        }
        if (field.isHasTradeAction()) {
            Button tradeBtn = new Button(trade);
            tradeBtn.setOnMouseClicked(e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    remove();
                    field.trade();
                }
            });
            tradeBtn.setTooltipText(ResourceUtil.get("${trade}","ui"));
            buttons.add(tradeBtn);
        }
        this.getChildren().addAll(buttons);
        double x;
        double y;
        for (int i = 0; i < buttons.size(); i++) {
            double alpha = (360.0/buttons.size())*i-90;
            alpha = alpha * (Math.PI/180.0);
            x = Math.cos(alpha)*120;
            y = Math.sin(alpha)*120;
            buttons.get(i).setTranslateX(x);
            buttons.get(i).setTranslateY(y);
        }
        Circle c = new Circle(240, Color.TRANSPARENT);
        c.setTranslateX(60);
        c.setTranslateY(60);
        this.getChildren().add(0,c);


        this.setOnMouseExited(e->remove());

    }

    public void remove() {
        if(!isRemoved) {
            isRemoved=true;
            ((FieldButton) this.getParent()).setWheel(null);
            ((FieldButton) this.getParent()).getChildren().remove(this);

        }


    }
}
