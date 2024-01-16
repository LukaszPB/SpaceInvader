package com.example.projekt_ztp.Strategy;

import com.example.projekt_ztp.StageProperties;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Enemy {

    protected int health;
    protected Image image = new Image("file:src/main/resources/com/example/projekt_ztp/Images/EnemyOne.png");
    protected double xPos,yPos;
    protected double enemyWidth = StageProperties.ENEMY_WIDTH,enemyHeight = StageProperties.ENEMY_HEIGHT;
    protected Rectangle border;
    protected MoveStrategy moveStrategy;

    protected void loadClasses(){
    }
    public void initBorder() {
        border = new Rectangle(xPos, yPos,enemyWidth,enemyHeight);
    }

    public void move(MoveStrategy moveStrategy){
        moveStrategy.move(this);
        initBorder();
    }
    public void move(){
        moveStrategy.move(this);
        initBorder();
    }

    public void setStrategy(MoveStrategy moveStrategy){
        this.moveStrategy = moveStrategy;
    }

    protected void shoot(){
        //STRZELANIE DO GRACZA
        System.out.println("Strzelam!");
    }

    protected boolean isPlayerInRange(){
        //SPRAWDZANIE CZY MOZNA STRZELIC DO GRACZA
        return false;
    }

    public void update(){
        if(isPlayerInRange()){
            shoot();
        }
    }

}
