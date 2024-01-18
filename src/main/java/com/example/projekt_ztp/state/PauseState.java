package com.example.projekt_ztp.state;

import javafx.scene.layout.Pane;

public class PauseState extends AppState{
    public PauseState(Pane pane) {
        super(pane);
    }

    @Override
    protected void setupShipMove() {
        ship.getGraphicRep().setOnKeyPressed(event -> {
        });
    }

    @Override
    public void enemyMove() {

    }

    @Override
    public void bulletsMove() {

    }
}
