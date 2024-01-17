package com.example.projekt_ztp;

import com.example.projekt_ztp.decorators.BasicModule;
import com.example.projekt_ztp.decorators.StrongerEngines;
import com.example.projekt_ztp.decorators.Upgrade;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class Ship {
    private static final Ship instance = new Ship();
    private double x=(StageProperties.GAME_WINDOW_WIDTH-StageProperties.SHIP_SIZE)/2;
    private double y=StageProperties.GAME_WINDOW_HEIGHT-1.5*StageProperties.SHIP_SIZE;
    private Upgrade upgrade = new BasicModule();
    private Button graphicRep;
    private Ship() {
        graphicRep = new Button();
        graphicRep.setMinSize(StageProperties.SHIP_SIZE,StageProperties.SHIP_SIZE);
        graphicRep.setMaxSize(StageProperties.SHIP_SIZE,StageProperties.SHIP_SIZE);

        Image shipImage = new Image("file:src/main/resources/com/example/projekt_ztp/Images/ship.png");
        String shipStyle = "-fx-background-image: url('" + shipImage.getUrl() + "'); " +
                "-fx-background-size: cover;";

        graphicRep.setStyle(shipStyle);

        graphicRep.setLayoutX(x);
        graphicRep.setLayoutY(y);
    }
    public static Ship getInstance() { return instance; }
    public void addUpgrade(Upgrade upgrade) {
        this.upgrade = new StrongerEngines(this.upgrade);
    }
    public void move(int shift) {
        if(x+shift < 10 || x+shift > StageProperties.GAME_WINDOW_WIDTH-StageProperties.SHIP_SIZE-10) {
            return;
        }
        x += upgrade.move(shift);
        graphicRep.setLayoutX(x);
    }
    public Bullet shot() {
        System.out.println(x+StageProperties.SHIP_SIZE/2 + "|" + 290);
        return upgrade.shot(new Bullet(x+StageProperties.SHIP_SIZE/2+12.5,StageProperties.GAME_WINDOW_HEIGHT-StageProperties.SHIP_SIZE-10));
    }
    public double getX() { return x; }
    public Button getGraphicRep() {
        return graphicRep;
    }
    public void setX(double x) {
        this.x = x;
        graphicRep.setLayoutX(x);
    }
}
