package com.example.projekt_ztp.state;

import javafx.scene.layout.Pane;

public class PauseState extends AppState{
    public PauseState(Pane pane) {
        super(pane);
        System.out.println(this + "STAN PAUZA");
    }

    @Override
    protected void setupShipMove() {
        ship.getGraphicRep().setOnKeyPressed(event -> {
        });
    }

    @Override
    public int enemyMove() {
        return 0;
    }

    @Override
    public void bulletsMove() {

    }
}
