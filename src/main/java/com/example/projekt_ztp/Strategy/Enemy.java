package com.example.projekt_ztp.Strategy;

import com.example.projekt_ztp.StageProperties;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Enemy {

    protected int health;
    protected Image image = new Image("file:src/main/resources/com/example/projekt_ztp/Images/EnemyOne.png");
    protected double xPos,yPos;
    protected double enemyWidth = StageProperties.ENEMY_WIDTH - 30.0,enemyHeight = StageProperties.ENEMY_HEIGHT - 30.0;
    protected Rectangle border;
    protected MoveStrategy moveStrategy;
    protected MoveLeft moveLeft = new MoveLeft();
    protected MoveRight moveRight = new MoveRight();
    protected ImagePattern imagePattern = new ImagePattern(image);

    public void reverseStrategy(){
        if(moveStrategy == moveLeft){
            moveStrategy = moveRight;
        }else{
            moveStrategy = moveLeft;
        }
    }


    protected void loadClasses(){
    }
    public void initBorder() {
        border = new Rectangle(xPos, yPos,enemyWidth,enemyHeight);
        //border.setFill(Color.BLUE);
        border.setFill(imagePattern);
    }

    public void move(MoveStrategy moveStrategy){
        moveStrategy.move(this);
        initBorder();
    }
    public boolean move(){
        moveStrategy.move(this);
        System.out.println(xPos + "xpos");
        //xPos = xPos +5;
        border.setLayoutX(xPos);
        //initBorder(); NIE DAWAĆ INIT BORDER
        return xPos > 500.0 || xPos < 5.0;
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

    public Rectangle getGraphicRep() {
        initBorder();
        return border;
    }
    public String getXandY(){
        return xPos + "|" + yPos;
    }
}
