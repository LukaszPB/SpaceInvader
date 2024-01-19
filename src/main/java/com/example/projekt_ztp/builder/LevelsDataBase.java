package com.example.projekt_ztp.builder;

import com.example.projekt_ztp.StageProperties;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class LevelsDataBase {
    private ArrayList<String> levelDescriptions = new ArrayList<>();
    private BoardBuilder[] builders = {new BuilderOne(),new BuilderTwo()};
    private int difficulty=1, theme=0;
    public LevelsDataBase(String path) {
        try {
            Scanner scannerLevels = new Scanner(new File(path));
            while (scannerLevels.hasNext()) {
                levelDescriptions.add(scannerLevels.next());
            }
            scannerLevels.close();

            Scanner scannerSettings = new Scanner(new File(StageProperties.SETTINGS_FILE_PATH));
            difficulty = Integer.parseInt(scannerSettings.next());
            theme = Integer.parseInt(scannerSettings.next());
            scannerSettings.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Level buildLevel(BoardBuilder builder, String description) {
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
    public Iterator<Level> iterator() {
        return new LevelIterator();
    }
    public int getDifficulty() { return difficulty; }
    private class LevelIterator implements java.util.Iterator<Level> {
        private int idx=-1;
        public Level next() {
            if(idx+difficulty>levelDescriptions.size()) {
                idx = levelDescriptions.size()-1;
            }
            else {
                idx+=difficulty;
            }
            return buildLevel(builders[theme].copy(),levelDescriptions.get(idx));
        }
        @Override
        public boolean hasNext() {
            //return idx+difficulty<levelDescriptions.size();
            return true;
        }
    }
}
