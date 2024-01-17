package com.example.projekt_ztp.builder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelsDataBase {
    private ArrayList<String> levelDescriptions = new ArrayList<>();
    public LevelsDataBase(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                levelDescriptions.add(scanner.next());
            }
            scanner.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Level buildLevel(BoardBuilder builder, String description) {
        String[] rows = description.split(";");
        for(String row : rows) {
            for(char character : row.toCharArray()) {
                switch (character) {
                    case '0' -> builder.nexColumn();
                    case '1' -> builder.addEnemy1();
                    case '2' -> builder.addEnemy2();
                    case 'A' -> builder.addObstacle();
                }
            }
            builder.nexLine();
        }
        return builder.getLevel();
    }

    public ArrayList<String> getLevelDescriptions() {
        return levelDescriptions;
    }
}
