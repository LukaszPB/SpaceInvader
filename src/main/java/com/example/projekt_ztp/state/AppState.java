package com.example.projekt_ztp.state;

import javafx.scene.layout.Pane;

public abstract class AppState {
    protected Pane pane;
    public AppState(Pane pane) {
        this.pane = pane;
    }
    protected abstract void setupShipMove();
    public abstract void enemyMove();
    public abstract void bulletsMove();
}
