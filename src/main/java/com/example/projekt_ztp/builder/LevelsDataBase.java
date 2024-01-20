package com.example.projekt_ztp.builder;

import com.example.projekt_ztp.controllers.SettingsController;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class LevelsDataBase {
    private ArrayList<String> levelDescriptions = new ArrayList<>();
    private BoardBuilder[] builders = {new BuilderOne(),new BuilderTwo()};
    private int difficulty=1, theme=0;
    public LevelsDataBase(String path) {
        // Uzyskaj klasę ClassLoader dla bieżącego kontekstu
        ClassLoader classLoader = getClass().getClassLoader();

        // Uzyskaj InputStream za pomocą ClassLoader, używając pełnej ścieżki
        InputStream levelsInputStream = classLoader.getResourceAsStream("com/example/projekt_ztp/files/levels");

        // Sprawdź, czy InputStream został poprawnie uzyskany
        if (levelsInputStream != null) {
            Scanner scannerLevels = new Scanner(levelsInputStream);
            while (scannerLevels.hasNext()) {
                levelDescriptions.add(scannerLevels.next());
            }
            scannerLevels.close();
        } else {
            System.err.println("Nie można odnaleźć pliku z poziomami: com/example/projekt_ztp/files/levels");
        }

        // Analogicznie pobierz InputStream dla pliku ustawień
        InputStream settingsInputStream = classLoader.getResourceAsStream("com/example/projekt_ztp/files/settings");
        if (settingsInputStream != null) {
            Scanner scannerSettings = new Scanner(settingsInputStream);
            if(!SettingsController.loadedTheme){
                difficulty = Integer.parseInt(scannerSettings.next());
                theme = Integer.parseInt(scannerSettings.next());
            }else{
                difficulty = SettingsController.difficulty;
                theme = SettingsController.theme;
            }

            System.out.println("difficulty->" + difficulty + "||" + "theme" + theme + "ZPLIKU");
            scannerSettings.close();
        } else {
            System.err.println("Nie można odnaleźć pliku ustawień: com/example/projekt_ztp/files/settings");
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
