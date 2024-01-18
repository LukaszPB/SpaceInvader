package com.example.projekt_ztp.state;

import com.example.projekt_ztp.Ship;
import javafx.scene.layout.Pane;

public abstract class AppState {
    protected Pane pane;
    protected Ship ship = Ship.getInstance();
    public AppState(Pane pane) {
        this.pane = pane;
        setupShipMove();
    }
    protected abstract void setupShipMove();
    public abstract int enemyMove();
    public abstract void bulletsMove();
}
