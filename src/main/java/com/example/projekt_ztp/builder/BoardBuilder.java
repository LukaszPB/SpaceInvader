package com.example.projekt_ztp.builder;

public interface BoardBuilder {
    void addEnemy1();
    void addEnemy2();
    void addObstacle();
    void nexColumn();
    void nexLine();
    BoardBuilder copy();
    Level getLevel();
}
