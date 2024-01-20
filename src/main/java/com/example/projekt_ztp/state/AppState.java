package com.example.projekt_ztp.state;

import com.example.projekt_ztp.Ship;
import javafx.scene.layout.Pane;

public abstract class AppState {
    protected Pane pane;
    public Ship ship = Ship.getInstance();
    public boolean upgradeChosen = false;
    public AppState(Pane pane) {
        this.pane = pane;
        setupShipMove();
        System.out.println("APPSTATE");
    }
    protected abstract void setupShipMove();
    public abstract int enemyMove();
    public abstract void bulletsMove();
}
