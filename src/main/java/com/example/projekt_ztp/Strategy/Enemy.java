package com.example.projekt_ztp.Strategy;

import com.example.projekt_ztp.StageProperties;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Enemy {

    protected int health;
    protected Image image = new Image("file:src/main/resources/com/example/projekt_ztp/Images/EnemyTwo.png");
    protected double xPos,yPos;
    protected double enemyWidth = StageProperties.ENEMY_WIDTH ,enemyHeight = StageProperties.ENEMY_HEIGHT ;
    protected Rectangle border;
    protected MoveStrategy moveStrategy;
    protected MoveLeft moveLeft = new MoveLeft();
    protected MoveRight moveRight = new MoveRight();
    protected ImagePattern imagePattern = new ImagePattern(image);

    public void reverseStrategy(){
        yPos += StageProperties.ENEMY_HEIGHT;
        if(moveStrategy == moveLeft){
            moveStrategy = moveRight;
        }else{
            moveStrategy = moveLeft;
        }
    }


    protected void loadClasses(){
    }
    public void initBorder() {
        border = new Rectangle(StageProperties.ENEMY_WIDTH,StageProperties.ENEMY_HEIGHT);
        border.setLayoutX(xPos);
        border.setLayoutY(yPos);
        //border.setFill(Color.BLUE);
        border.setFill(imagePattern);
    }

    public boolean move(MoveStrategy moveStrategy){
        moveStrategy.move(this);
        border.setLayoutX(xPos);
        border.setLayoutY(yPos);
        return xPos > 500 || xPos < 15.0;
    }
    public boolean move(){
        moveStrategy.move(this);
        //System.out.println(xPos + "xpos");
        //xPos = xPos +5;
        border.setLayoutX(xPos);
        border.setLayoutY(yPos);
        //initBorder(); NIE DAWAĆ INIT BORDER
        return xPos > 500.0 || xPos <= 24.0;
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

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }
}
