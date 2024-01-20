package com.example.projekt_ztp.observer;

import javafx.scene.control.Label;

public class Score implements Observer{
    private int score=0;
    private Label label;
    public Score() {
        label = new Label();
        label.getStyleClass().add("message");
        label.setText(Integer.toString(score));
        label.setLayoutX(10);
        label.setLayoutY(10);
    }
    @Override
    public void update(Event event) {
        if(event instanceof NewLevel) {
            score += 100*event.getData();
        }
        else if(event instanceof EnemyEliminated) {
            score += event.getData();
        }
        label.setText(Integer.toString(score));
    }
    public int getScore() { return score; }
    public Label getLabel() { return label; }
}
